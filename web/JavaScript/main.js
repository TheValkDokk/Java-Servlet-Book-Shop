var loginBt = document.getElementById('loginBt');
var createBt = document.getElementById('createBt');
var editBt = document.getElementById('edit');
var cartBt = document.getElementById('cartBt');
window.onclick = function (event) {
    if (event.target === loginBt || event.target === createBt || event.target === editBt || event.target === cartBt) {
        loginBt.style.display = "none";
        createBt.style.display = "none";
        editBt.style.display = "none";
        cartBt.style.display = "none";
    }
};


function tabOpen(evt, CateName) {
    var i, tabcontent, tablinks, a;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    document.getElementById(CateName).style.display = "block";
    evt.currentTarget.className += " active";

}

function searchByName(param) {
    var search = param.value;
    console.log(search);
    $.ajax({
        url: "/BookShop/SearchByAjax",
        type: "get", //send it through get method
        data: {
            search: search
        },
        success: function (data) {
            var row = document.getElementById("content");
            row.innerHTML = data;
        },
        error: function (xhr) {
            //Do Something to handle error
        }
    });
}

var addItemId = 0;
var cartCon = document.getElementById('CartItemList');
var list = new Array();
function addtoCart(itemid) {
    var bookImg = itemid.children[0].children[0].src;
    var bookTitle = itemid.children[1].children[0].textContent;
    var bookPrice = itemid.children[1].children[3].textContent;
    var bookID = itemid.children[1].children[4].textContent;
    for (i = 0; i < list.length + 1; i++) {
        if (list[i] === bookID) {
            UpdateQuantity(bookID);
            break;
        }
        if (i === list.length) {
            AddnewRow(itemid.id, bookImg, bookTitle, bookPrice, bookID);
            list.push(bookID);
            break;
        }
    }
    totalPrice();
}

function totalPrice() {
    var f = 0;
    var totalprice = 0;
    var itemList = document.getElementById('CartItemList');
    var itemcount = itemList.getElementsByTagName('tr');
    var price = itemList.getElementsByTagName('strong');

    var i = 0;
    for (f = 0; f < itemcount.length; f++) {
        totalprice += Math.imul(price[i].textContent, price[i + 1].textContent);
        i += 2;
    }
    console.log(totalprice);
    document.getElementById('priceofall').innerHTML = totalprice + '$';
    var totalVatIncluded = totalprice + 10;
    document.getElementById('priceoftotal').innerHTML = totalVatIncluded + '$';
}

function UpdateQuantity(id) {
    for (i = 0; i < list.length; i++) {
        console.log(cartCon.children[i]);
        if (cartCon.children[i].className === id) {
            ++cartCon.children[i].children[2].children[1].textContent;
        }
    }
}

function inCQuan(id) {
    ++id.parentElement.children[1].textContent;
    totalPrice();
}

function deCQuan(id) {
    --id.parentElement.children[1].textContent;
    if (id.parentElement.children[1].textContent == 0)
        removeCart(id);
    totalPrice();
}

function AddnewRow(id, bookImg, bookTitle, bookPrice) {
    var tr = document.createElement('tr');
    tr.setAttribute("class", id);
    var insideTr = `<th scope="row">
                                                    <div class="p-2">
                                                        <img src="${bookImg}" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                        <div class="ml-3 d-inline-block align-middle">
                                                            <h5 class="mb-0"> <a id="title" href="#" class="text-dark d-inline-block">${bookTitle}</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                        </div>
                                                    </div>
                                                </th>
                                                <td class="align-middle"><strong>${bookPrice}</strong></td>
                                                <td class="align-middle" id="cart+${id}">
                                                    <a onclick="deCQuan(this)"><button class="btnSub">-</button></a> 
                                                    <strong data-target="quantity">1</strong>
                                                    <a onclick="inCQuan(this)"><button class="btnAdd">+</button></a>
                                                </td>
                                                <td class="align-middle">
                                                    <button type="button" onclick="removeCart(this)" id="removeCartBt" value="${id}" class="btn btn-danger">Delete</button>
                                                </td>`;
    tr.innerHTML = insideTr;
    cartCon.appendChild(tr);
}

function removeCart(e) {
    var id = e.parentElement.parentElement.className;
    e.parentElement.parentElement.remove();
    for (var i = list.length - 1; i >= 0; i--) {
        if (list[i] === id) {
            list.splice(i, 1);
        }
    }
    totalPrice();
}