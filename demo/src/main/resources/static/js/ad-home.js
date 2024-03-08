document.addEventListener('DOMContentLoaded', function() {
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
    
  var toggleButtons = document.querySelectorAll('.toggle');

  toggleButtons.forEach(function(button) {
    button.addEventListener('click', function() {
      var menuWrapper = this.nextElementSibling;
      var leftContainer = this.closest('.left');
      var toggleBtn = this;
      var isActive = this.classList.contains('active');

      if (!isActive) {
        menuWrapper.style.display = 'block';
        menuWrapper.style.width = '200px';
        toggleBtn.style.marginLeft = '90px';
        leftContainer.style.backgroundColor = '#f0f0f0';
        toggleBtn.classList.add('active');
      } else {
        menuWrapper.style.display = 'none';
        menuWrapper.style.width = '0';
        toggleBtn.style.marginLeft = '0';
        leftContainer.style.backgroundColor = 'transparent';
        toggleBtn.classList.remove('active');
      }
    });
  });

  var previousSelectedItem; // Lưu trữ phần tử được chọn trước đó

  var menuItems = document.querySelectorAll('.vertical-menu ul li');

  menuItems.forEach(function(item) {
    item.addEventListener('click', function(event) {
      event.preventDefault();

      // Lấy phần tử con chứa icon và văn bản
      var parentDiv = this.querySelector('div');

      if (!parentDiv) return; 

      // Kiểm tra màu nền của phần tử hiện tại
      var currentColor = window.getComputedStyle(parentDiv).backgroundColor;

      // Nếu màu nền hiện tại không phải là trắng (#ffffff)
      if (currentColor !== 'rgb(255, 255, 255)') {
        // Đặt màu nền của phần tử hiện tại thành trắng
        parentDiv.style.backgroundColor = '#ffffff';
        
        // Nếu có phần tử được chọn trước đó, đặt màu nền của nó về màu cũ
        if (previousSelectedItem) {
          previousSelectedItem.style.backgroundColor = ''; 
        }

        // Lấy tên file HTML từ thuộc tính data-info của phần tử hiện tại
        // var htmlFile = parentDiv.dataset.info;

        // // Load nội dung của file HTML tương ứng và hiển thị trong phần tử chứa thông tin
        // var infoContainer = document.querySelector('.info-container');
        // fetch(htmlFile)
        //   .then(response => response.text())
        //   .then(data => {
        //     infoContainer.innerHTML = data;
        //   });

        // Lưu trữ phần tử hiện tại vào biến previousSelectedItem để sử dụng cho lần click tiếp theo
        previousSelectedItem = parentDiv;
      }
      
    });
  });

  

  var toggleButton = document.querySelector('.toggle');
  var menuWrapper = document.querySelector('.menu-wrapper');
  var infoContainer = document.querySelector('.info-container');

  toggleButton.addEventListener('click', function() {
    if (menuWrapper.classList.contains('collapsed')) {
      infoContainer.innerHTML = ''; // Ẩn dữ liệu khi menu được thu gọn
      var selectedButton = document.querySelector('.vertical-menu ul li div[data-info]:hover');
      if (selectedButton) {
        previousSelectedItem = selectedButton;
      }
    }

  menuWrapper.classList.toggle('collapsed');
  });
  
});


