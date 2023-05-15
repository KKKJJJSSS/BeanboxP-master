package Beanbox.Beanbox;

import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TextExtractor {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/extract")
    public String extractText(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        try {
            // 파일을 로컬 디스크에 저장
            Path tempFile = Files.createTempFile("temp-", "-" + file.getOriginalFilename());
            try (OutputStream os = Files.newOutputStream(tempFile)) {
                os.write(file.getBytes());
            }

            // 저장한 파일을 이용하여 Image 생성
            BufferedImage img = ImageIO.read(tempFile.toFile());
            BufferedImage resizedImg = resizeImage(img, 800, 600); // 이미지의 크기를 800x600으로 조정
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImg, "jpg", baos);
            ByteString imgBytes = ByteString.copyFrom(baos.toByteArray());
            Image image = Image.newBuilder().setContent(imgBytes).build();

            // API 호출을 위한 ImageAnnotatorClient 생성
            try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {

                // API 요청 객체 생성
                List<AnnotateImageRequest> requests = new ArrayList<>();
                ImageContext imageContext = ImageContext.newBuilder().addLanguageHints("ko").build();
                Feature feature = Feature.newBuilder().setType(Feature.Type.DOCUMENT_TEXT_DETECTION).build();
                AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                        .addFeatures(feature)
                        .setImage(image)
                        .setImageContext(imageContext)
                        .build();
                requests.add(request);

                // API 호출 및 응답 받기
                List<AnnotateImageResponse> responses = client.batchAnnotateImages(requests).getResponsesList();

                // 추출한 텍스트 가져오기
                String extractedText = "";
                for (AnnotateImageResponse response : responses) {
                    if (response.hasError()) {
                        System.err.println("Error: " + response.getError().getMessage());
                        return "error"; // 오류 처리
                    }
                    TextAnnotation textAnnotation = response.getFullTextAnnotation();
                    if (textAnnotation != null) {
                        extractedText += textAnnotation.getText();
                    }
                }

                // 모델에 추출한 텍스트 추가
                model.addAttribute("extractedText", extractedText);

            } // API 클라이언트가 자동으로 종료됩니다.

            // 임시 파일 삭제
            Files.deleteIfExists(tempFile);

            return "result"; // 결과 페이지로 이동
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
}