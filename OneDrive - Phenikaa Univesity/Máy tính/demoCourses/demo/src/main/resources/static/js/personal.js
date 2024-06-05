document.addEventListener("DOMContentLoaded", function() {
    var accountIcon = document.querySelector('.account-login');
    var submenu = document.querySelector('.submenu');
    
    accountIcon.addEventListener('click', function(event) {
      event.preventDefault(); // Ngăn chặn hành động mặc định của thẻ a
  
      if (submenu.classList.contains('show')) {
        submenu.classList.remove('show'); // Nếu submenu đã hiển thị, ẩn nó đi
      } else {
        submenu.classList.add('show'); // Nếu chưa hiển thị, thêm lớp 'show' để hiển thị nó
      }
    });
  
    // Ẩn submenu khi người dùng nhấp chuột ra ngoài
    document.addEventListener('click', function(event) {
      if (!accountIcon.contains(event.target) && !submenu.contains(event.target)) {
        submenu.classList.remove('show');
      }
    });
  });
  