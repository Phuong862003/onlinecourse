document.addEventListener('DOMContentLoaded', function() {
    var buttonHome = document.querySelector('.button-home');
    var buttonCourse = document.querySelector('.button-course');
    var buttonMember = document.querySelector('.button-member');
    var buttonUser = document.querySelector('.button-user');
    var divRightHome = document.querySelector('.right-home');
    var divRightCourse = document.querySelector('.right');
    var divRightMember = document.querySelector('.right-member');
    var divRightUser = document.querySelector('.right-user');

    var currentIndex = 0;

    var contents = [
        { button: buttonHome, divRight: divRightHome },
        { button: buttonCourse, divRight: divRightCourse },
        { button: buttonMember, divRight: divRightMember },
        { button: buttonUser, divRight: divRightUser}
    ];

    function toggleContent(index) {
        contents.forEach(function(item, i) {
            if (i === index) {
                item.divRight.style.display = 'block';
            } else {
                item.divRight.style.display = 'none';
            }
        });
    }

    function updateContent(index) {
        currentIndex = index;
        toggleContent(currentIndex);
    }

    buttonHome.addEventListener('click', function() {
        if (currentIndex !== 0) {
            updateContent(0);
        }
    });

    buttonCourse.addEventListener('click', function() {
        if (currentIndex !== 1) {
            updateContent(1);
        }
    });

    buttonMember.addEventListener('click', function() {
        if (currentIndex !== 2) {
            updateContent(2);
        }
    });

    buttonUser.addEventListener('click', function() {
        if (currentIndex !== 3) {
            updateContent(3);
        }
    });

    toggleContent(currentIndex);
});
