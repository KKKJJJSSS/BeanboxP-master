document.addEventListener('DOMContentLoaded', function () {
    var addToCartButtons = document.querySelectorAll('.btn-outline-secondary');

    // 장바구니 버튼 클릭 이벤트
    addToCartButtons.forEach(function (button) {
        button.addEventListener('click', function (e) {
            e.preventDefault();
            var coffeeName = e.target.getAttribute('data-coffee-name');
            addToCart(coffeeName);
        });
    });
});

function addToCart(coffeeName) {

    // Ajax 요청을 사용하여 coffee_name 값을 서버로 전송
    fetch("/auth/add-to-cart", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: coffeeName
    })
        .then(response => {
            if (response.ok) {
                console.log("장바구니에 추가되었습니다.");
                alert("장바구니에 추가되었습니다."); // 팝업창 띄우기
            } else {
                console.log("이미 추가된 상품입니다.");
                alert("이미 추가된 상품입니다."); // 팝창 띄우기
            }
        })
        .catch(error => {
            console.log("에러:", error);
            alert("에러가 발생하였습니다."); // 팝업창 띄우기
        });
}

//체크박스 처리
$(document).ready(function() {

    // 체크박스 allSelect 가 클릭되었을 때
    $('#allSelect').click(function() {
        if ($(this).prop('checked')) {
            $('#ice').show();
            $('#hot').show();
            $('#iceSelect').prop('checked', false);
            $('#hotSelect').prop('checked', false);
        } else {
            $('#ice').hide();
            $('#hot').hide();
        }
    }).click(); // allSelect 클릭 이벤트 발생

    // 체크박스 iceSelect 가 클릭되었을 때
    $('#iceSelect').click(function() {
        if ($('#allSelect').prop('checked')) {
            $('#ice').hide();
            $('#hot').hide();
        }
        if ($(this).prop('checked')) {
            $('#ice').show();
            $('#allSelect').prop('checked', false);
        } else {
            $('#ice').hide();
        }
    });

    // 체크박스 hotSelect 가 클릭되었을 때
    $('#hotSelect').click(function() {
        if ($('#allSelect').prop('checked')) {
            $('#ice').hide();
            $('#hot').hide();
        }
        if ($(this).prop('checked')) {
            $('#hot').show();
            $('#allSelect').prop('checked', false);
        } else {
            $('#hot').hide();
        }
    });
});