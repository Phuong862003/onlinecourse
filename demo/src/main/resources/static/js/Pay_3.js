document.addEventListener("DOMContentLoaded", function() {
    const images = document.querySelectorAll(".QA .image img");
    const firstImage = document.querySelector(".QA .image1 img");
    const secondImage = document.querySelector(".QA .image img");

    // Ẩn ảnh thứ hai ban đầu
    secondImage.style.display = "none";

    // Sự kiện click cho phần QA
    document.querySelector(".QA").addEventListener("click", function(event) {
        // Ngăn chặn hành vi mặc định của thẻ <a> khi được nhấp (chẳng hạn chuyển hướng)
        event.preventDefault();

        // Kiểm tra xem ảnh hiện tại đang được hiển thị là ảnh nào
        const isSecondImageVisible = secondImage.style.display === "block";

        // Nếu ảnh thứ hai đang hiển thị, chuyển sang hiển thị ảnh thứ nhất và ngược lại
        if (isSecondImageVisible) {
            secondImage.style.display = "none";
            firstImage.style.display = "block";
        } else {
            firstImage.style.display = "none";
            secondImage.style.display = "block";
        }
    });
});
