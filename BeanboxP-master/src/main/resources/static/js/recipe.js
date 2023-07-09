function redirectToMenu() {
    // 세션에서 가져온 username을 확인합니다.
    const username = '<%= (String) session.getAttribute("username") %>';

    // 로그인되어 있는 경우
    if (username && username !== "") {
        location.href = "/auth/menu";
    }
    // 로그인되어 있지 않은 경우
    else {
        location.href = "/auth/login";
    }
}