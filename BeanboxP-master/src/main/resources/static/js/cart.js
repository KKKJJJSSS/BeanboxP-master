document.addEventListener('DOMContentLoaded', function () {
    var removeFromCartButtons = document.querySelectorAll('.btn-remove-from-cart');

    // Remove from cart 버튼 클릭 이벤트
    removeFromCartButtons.forEach(function (button) {
        button.addEventListener('click', function (e) {
            e.preventDefault();
            var cartNumber = e.target.getAttribute('data-cart-number');
            removeFromCart(cartNumber);
        });
    });
});

function removeFromCart(cartNumber) {
    // Fetch API를 사용하여 삭제 요청을 보낸 후 응답 처리
    fetch("/auth/remove-from-cart", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({cart_number: cartNumber})
    })
        .then(response => {
            if (response.ok) {
                console.log("상품이 삭제되었습니다.");
                alert("상품이 삭제되었습니다.");
                // 성공적으로 상품이 삭제되면 페이지 새로고침 또는 DOM 조작
            } else {
                console.log("상품을 삭제하는 데 실패했습니다.");
                alert("상품을 삭제하는 데 실패했습니다.");
            }
        })
        .catch(error => {
            console.log("에러:", error);
            alert("에러가 발생하였습니다.");
        });
}
