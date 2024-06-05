document.addEventListener('DOMContentLoaded', function() {
    var buttonCourse = document.querySelector('.button-member'); // Chọn nút "Course"
    var divRight = document.querySelector('.right-member'); // Chọn div right
    var isDivRightVisible = false; // Biến để theo dõi trạng thái hiển thị của div right
  
    // Lắng nghe sự kiện click trên nút "Course"
    buttonCourse.addEventListener('click', function(event) {
      event.preventDefault(); // Ngăn chặn hành động mặc định của thẻ a
  
      // Nếu div right đang ẩn, hiển thị nó ra
      if (!isDivRightVisible) {
        divRight.style.display = 'block';
        isDivRightVisible = true; // Cập nhật trạng thái hiển thị của div right
      } else { // Nếu div right đang hiển thị, ẩn nó đi
        divRight.style.display = 'none';
        isDivRightVisible = false; // Cập nhật trạng thái hiển thị của div right
      }
    });
  });
  

//   them
  document.addEventListener("DOMContentLoaded", function() {
    const addMemberButton = document.querySelector(".New-member");
    const memberForm = document.querySelector(".themmoi-member");
    const img = document.getElementById('img');
    const fileInput = document.getElementById('input');
    const fullNameInput = document.getElementById('fullName');
    const giaInput = document.getElementById('gia');

    addMemberButton.addEventListener("click", function() {
        memberForm.style.display = "block";
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
            memberForm.style.display = "block"; // Hiển thị form
        }
    };

    const cancelButton = document.querySelector(".huy-member");
    cancelButton.addEventListener("click", function() {
        memberForm.style.display = "none";
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
    const addMemberButton = document.querySelector(".Sua-member");
    const memberForm = document.querySelector(".themmoi-member");
    const img = document.getElementById('img');
    const fileInput = document.getElementById('input');
    const fullNameInput = document.getElementById('fullName');
    const giaInput = document.getElementById('gia');

    addMemberButton.addEventListener("click", function() {
        memberForm.style.display = "block";
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
            memberForm.style.display = "block"; // Hiển thị form
        }
    };

    const cancelButton = document.querySelector(".huy-member");
    cancelButton.addEventListener("click", function() {
        memberForm.style.display = "none";
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