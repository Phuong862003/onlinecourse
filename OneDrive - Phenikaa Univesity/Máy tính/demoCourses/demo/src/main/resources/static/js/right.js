document.addEventListener('DOMContentLoaded', function() {
    var buttonCourse = document.querySelector('.button-course'); // Chọn nút "Course"
    var divRight = document.querySelector('.right'); // Chọn div right
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
  