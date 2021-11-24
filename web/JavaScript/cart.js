//        $(function () {
//            $(document).on('click', 'input[data-role=changeQuan]', function () {
//                var id = $(this).data('id');
//                var quantity = $('#cart' + id).children('input[data-target=quantity]').val();
//                $.ajax({
//                    url: "/BookShop/UpdateCartController",
//                    type: "post",
//                    data: {
//                        bookID: id,
//                        quantity: quantity
//                    },
//                    success: function (data) {
//                        var row = document.getElementById("totalPrice");
//                        row.innerHTML = data;
//                        contentLoaded(0);
//                    }, error: function (xhr) {
//                    }
//                });
//            });
//        });

var noti = document.querySelector('h1');
var button = document.getElementsByClassName('Ibuy');
console.log(button);
for (var but of button) {
    but.addEventListener('click', (e) => {
        var add = Number(noti.getAttribute('data-count') || 0);
        noti.setAttribute('data-count', add + 1);
        noti.classList.add('zero');
    });
}
;