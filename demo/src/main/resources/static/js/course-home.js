// them
document.addEventListener("DOMContentLoaded", function() {
    const addCourseButton = document.getElementById("addCourseButton");
    const courseForm = document.getElementById("courseForm");
    const img = document.getElementById('img');
    const fileInput = document.getElementById('input');
    const fullNameInput = document.getElementById('fullName');
    const tacgiaInput = document.getElementById('tacgia');
    const giaInput = document.getElementById('gia');

    addCourseButton.addEventListener("click", function() {
        courseForm.style.display = "block";
    });

    fileInput.onchange = (e) => {
        if (fileInput.files && fileInput.files[0]) {
            const reader = new FileReader();
            reader.onload = (event) => {
                img.src = event.target.result;

                // Tính toán kích thước ảnh
                const maxWidth = 260; // Độ rộng tối đa mong muốn
                const maxHeight = 132; // Chiều cao tối đa mong muốn

                // Tạo một hình ảnh ảo để lấy kích thước thực của ảnh
                const tempImg = new Image();
                tempImg.onload = function() {
                    const widthRatio = maxWidth / tempImg.width;
                    const heightRatio = maxHeight / tempImg.height;
                    const ratio = Math.min(widthRatio, heightRatio);
                    const newWidth = tempImg.width * ratio;
                    const newHeight = tempImg.height * ratio;

                    // Cài đặt kích thước mới cho ảnh
                    img.style.width = newWidth + 'px';
                    img.style.height = newHeight + 'px';
                };
                tempImg.src = event.target.result;
            };
            reader.readAsDataURL(fileInput.files[0]);
            courseForm.style.display = "block"; // Hiển thị form
        }
    };

    const cancelButton = document.querySelector(".huy");
    cancelButton.addEventListener("click", function() {
        courseForm.style.display = "none";
        // Đặt lại giá trị của các trường nhập liệu về rỗng khi nhấn Hủy
        fullNameInput.value = "";
        tacgiaInput.value = "";
        giaInput.value = "";
        
        // Đặt lại giá trị của file ảnh về mặc định
        fileInput.value = null;
        // Đặt lại giá trị của ảnh về mặc định
        img.src = "choose.png";
    });
});


// sua
document.addEventListener("DOMContentLoaded", function() {
    const addCourseButton = document.getElementById("editCourseButton");
    const courseForm = document.getElementById("courseForm");
    const img = document.getElementById('img');
    const fileInput = document.getElementById('input');
    const fullNameInput = document.getElementById('fullName');
    const tacgiaInput = document.getElementById('tacgia');
    const giaInput = document.getElementById('gia');

    addCourseButton.addEventListener("click", function() {
        courseForm.style.display = "block";
    });

    fileInput.onchange = (e) => {
        if (fileInput.files && fileInput.files[0]) {
            const reader = new FileReader();
            reader.onload = (event) => {
                img.src = event.target.result;

                // Tính toán kích thước ảnh
                const maxWidth = 260; // Độ rộng tối đa mong muốn
                const maxHeight = 132; // Chiều cao tối đa mong muốn

                // Tạo một hình ảnh ảo để lấy kích thước thực của ảnh
                const tempImg = new Image();
                tempImg.onload = function() {
                    const widthRatio = maxWidth / tempImg.width;
                    const heightRatio = maxHeight / tempImg.height;
                    const ratio = Math.min(widthRatio, heightRatio);
                    const newWidth = tempImg.width * ratio;
                    const newHeight = tempImg.height * ratio;

                    // Cài đặt kích thước mới cho ảnh
                    img.style.width = newWidth + 'px';
                    img.style.height = newHeight + 'px';
                };
                tempImg.src = event.target.result;
            };
            reader.readAsDataURL(fileInput.files[0]);
            courseForm.style.display = "block"; // Hiển thị form
        }
    };

    const cancelButton = document.querySelector(".huy");
    cancelButton.addEventListener("click", function() {
        courseForm.style.display = "none";
        // Đặt lại giá trị của các trường nhập liệu về rỗng khi nhấn Hủy
        fullNameInput.value = "";
        tacgiaInput.value = "";
        giaInput.value = "";
        
        // Đặt lại giá trị của file ảnh về mặc định
        fileInput.value = null;
        // Đặt lại giá trị của ảnh về mặc định
        img.src = "choose.png";
    });
});
