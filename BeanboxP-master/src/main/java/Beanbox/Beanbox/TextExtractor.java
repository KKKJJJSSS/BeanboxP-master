package Beanbox.Beanbox;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
            // MultipartFile을 byte 배열로 변환
            byte[] fileBytes = StreamUtils.copyToByteArray(file.getInputStream());

            // byte 배열을 이용하여 Image 생성
            ByteString imgBytes = ByteString.copyFrom(fileBytes);
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
                BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
                List<AnnotateImageResponse> responses = response.getResponsesList();

                // 추출한 텍스트 가져오기
                String extractedText = "";
                for (AnnotateImageResponse annotateResponse : responses) {
                    if (annotateResponse.hasError()) {
                        System.err.println("Error: " + annotateResponse.getError().getMessage());
                        return "error"; // 오류 처리
                    }
                    for (EntityAnnotation annotation : annotateResponse.getTextAnnotationsList()) {
                        extractedText += annotation.getDescription();
                    }
                }

                // 모델에 추출한 텍스트 추가
                model.addAttribute("extractedText", extractedText);

            } // API 클라이언트가 자동으로 종료됩니다.

            return "result"; // 결과 페이지로 이동
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
}