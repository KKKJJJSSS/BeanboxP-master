document.addEventListener('DOMContentLoaded', function () {
    var removeFromCartButtons = document.querySelectorAll('.btn-remove-from-cart');

    removeFromCartButtons.forEach(function (button) {
        button.addEventListener('click', function (e) {
            e.preventDefault();
            var cartNumber = e.target.getAttribute('data-cart-number');
            removeFromCart(cartNumber);
        });
    });
});

function removeFromCart(cartNumber) {
    const formData = new FormData();
    formData.append('cart_number', cartNumber);

    fetch("/auth/remove-from-cart", {
        method: "POST",
        body: formData
    })
        .then(response => {
            if (response.ok) {
                console.log("상품이 삭제되었습니다.");
                alert("상품이 삭제되었습니다.");
                location.reload(); // 페이지 새로고침
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
