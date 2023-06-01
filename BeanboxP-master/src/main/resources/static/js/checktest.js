//장바구니 처리
function addToCart() {
    // 팝업을 표시합니다.
    let alertDiv = document.getElementById("alert");
    alertDiv.style.display = "block";
    alertDiv.style.position = "fixed"; // 고정 위치를 사용하도록 변경

    // 브라우저 창 가운데 위치시킵니다.
    let windowWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
    let windowHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;

    // 요소의 크기를 얻습니다.
    let alertSize = alertDiv.getBoundingClientRect();

    alertDiv.style.left = (windowWidth - alertSize.width) / 2 + 'px';
    alertDiv.style.top = (windowHeight - alertSize.height) / 2 + 'px';

    // 오버레이를 활성화합니다.
    let overlay = document.getElementById("overlay");
    overlay.style.display = "block";
    overlay.style.position = "fixed"; // 고정 위치를 사용하도록 변경
    overlay.style.top = 0;
    overlay.style.left = 0;
    overlay.style.width = "100%";
    overlay.style.height = "100%";

    // 스크롤을 비활성화합니다.
    document.body.style.overflow = "hidden";

}

function closeAlert() {
    // 팝업을 숨깁니다.
    let alertDiv = document.getElementById("alert");
    alertDiv.style.display = "none";

    // 오버레이를 비활성화합니다.
    let overlay = document.getElementById("overlay");
    overlay.style.display = "none";

    // 스크롤을 재활성화합니다.
    document.body.style.overflow = "auto";
}

//체크박스 처리
$(document).ready(function() {

    // 체크박스 allSelect 가 클릭되었을 때
    $('#allSelect').click(function() {
        if ($(this).prop('checked')) {
            $('#latteCoffee2').hide();
            $('#cappuccinoCoffee2').hide();
            $('#latteCoffee').show();
            $('#cappuccinoCoffee').show();
            $('#latteSelect').prop('checked', false);
            $('#cappuccinoSelect').prop('checked', false);
        } else {
            $('#latteCoffee').hide();
            $('#cappuccinoCoffee').hide();
        }
    }).click(); // allSelect 클릭 이벤트 발생

    // 체크박스 latteSelect 가 클릭되었을 때
    $('#latteSelect').click(function() {
        if ($('#allSelect').prop('checked')) {
            $('#latteCoffee').hide();
            $('#cappuccinoCoffee').hide();
        }
        if ($(this).prop('checked')) {
            $('#latteCoffee').show();
            $('#allSelect').prop('checked', false);
        } else {
            $('#latteCoffee').hide();
        }
    });

    // 체크박스 cappuccinoSelect 가 클릭되었을 때
    $('#cappuccinoSelect').click(function() {
        if ($('#allSelect').prop('checked')) {
            $('#latteCoffee').hide();
            $('#cappuccinoCoffee').hide();
        }
        if ($(this).prop('checked')) {
            $('#cappuccinoCoffee').show();
            $('#allSelect').prop('checked', false);
        } else {
            $('#cappuccinoCoffee').hide();
        }
    });



    // 체크박스 allSelect2 가 클릭되었을 때
    $('#allSelect2').click(function() {
        if ($(this).prop('checked')) {
            $('#latteCoffee').hide();
            $('#cappuccinoCoffee').hide();
            $('#latteCoffee2').show();
            $('#cappuccinoCoffee2').show();
            $('#latteSelect2').prop('checked', false);
            $('#cappuccinoSelect2').prop('checked', false);
        } else {
            $('#latteCoffee2').hide();
            $('#cappuccinoCoffee2').hide();
        }
    })

    // 체크박스 latteSelect2 가 클릭되었을 때
    $('#latteSelect2').click(function() {
        if ($('#allSelect2').prop('checked')) {
            $('#latteCoffee2').hide();
            $('#cappuccinoCoffee2').hide();
        }
        if ($(this).prop('checked')) {
            $('#latteCoffee2').show();
            $('#allSelect2').prop('checked', false);
        } else {
            $('#latteCoffee2').hide();
        }
    });

    // 체크박스 cappuccinoSelect2 가 클릭되었을 때
    $('#cappuccinoSelect2').click(function() {
        if ($('#allSelect2').prop('checked')) {
            $('#latteCoffee2').hide();
            $('#cappuccinoCoffee2').hide();
        }
        if ($(this).prop('checked')) {
            $('#cappuccinoCoffee2').show();
            $('#allSelect2').prop('checked', false);
        } else {
            $('#cappuccinoCoffee2').hide();
        }
    });

});

function toggleCheckboxes(button) {
    var checkboxes = document.querySelectorAll(".form-check-inline");
    if (button === "A") {
        checkboxes[0].style.display = "inline-block";
        checkboxes[1].style.display = "inline-block";
        checkboxes[2].style.display = "inline-block";
        checkboxes[3].style.display = "none";
        checkboxes[4].style.display = "none";
        checkboxes[5].style.display = "none";
        $('#allSelect').prop('checked', true).trigger('click').trigger('click')
        // $('#allSelect2').prop('checked', true);는 단순히 allSelect2 체크박스를 체크상태로 만들어주는 코드.
        // 반면에 $('#allSelect2').prop('checked', true).trigger('click');는 체크박스를 체크한 후,
        // .click() 이벤트를 실행시켜주기 때문에 함수 내부 이벤트도 실행이 됨.
        // 이렇게 해서 전체 선택 버튼(allSelect2)이 체크되었을 때에도, 해당 체크박스에 연결된 이벤트가 실행되도록 만들 수 있음.
    } else if (button === "B") {
        checkboxes[0].style.display = "none";
        checkboxes[1].style.display = "none";
        checkboxes[2].style.display = "none";
        checkboxes[3].style.display = "inline-block";
        checkboxes[4].style.display = "inline-block";
        checkboxes[5].style.display = "inline-block";
        $('#allSelect2').prop('checked', true).trigger('click').trigger('click')
    }
}