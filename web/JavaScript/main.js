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
    var search = "";
    if(param!=="")
    search = param.value;
    $.ajax({
        url: "/BookShop/SearchByAjax",
        type: "get",
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
var vouncherOut = 0;
var addItemId = 0;
var cartCon = document.getElementById('CartItemList');
var list = new Array();

function useVouncher(param) {
    var v10 = 'get10';
    var v20 = 'get20';
    var v30 = 'get30';
    var v40 = 'get40';
    var v50 = 'get50';
    var v60 = 'get60';
    var v70 = 'get70';
    var v80 = 'get80';
    var v90 = 'get90';
    var v100 = 'get100';
    var v = ["get10", "get20", "get30", "get40", "get50", "get60", "get70", "get80", "get90", "get100"];
    var vouncherIn = document.getElementById('vouncherInput').value;
    if (vouncherIn !== "") {
        for (i = 0; i < v.length; i++) {
            if (vouncherIn === v[i]) {
                vouncherOut = 100 - v[i].slice(3, 6);
                console.log(vouncherOut);
            }
        }
    } else {
        vouncherOut = 0;
    }
    totalPrice();
    callNoti("Book Added","Success");
}

function addtoCart(itemid) {
    var bookImg = itemid.children[0].children[0].src;
    var bookTitle = itemid.children[1].children[0].textContent;
    var bookPrice = itemid.children[1].children[6].textContent;
    var bookID = itemid.children[1].children[7].textContent;
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

function removeSpan() {
    if ($('#notbook').length > 0) {
        $('#notbook').remove();
    }
}

function totalPrice() {
    var f = 0;
    var totalprice = 0;
    var itemList = document.getElementById('CartItemList');
    var itemcount = itemList.getElementsByTagName('tr');
    var price = itemList.getElementsByTagName('strong');
    var quantity = document.getElementsByClassName('quaninput');
    var i = 0;
    for (f = 0; f < itemcount.length; f++) {
        totalprice += Math.imul(price[i].textContent, quantity[i].value);
        i += 1;
    }
    document.getElementById('priceofall').innerHTML = totalprice + '$';
    var totalVatIncluded = (totalprice * 110) / 100;
    if (vouncherOut > 0) {
        totalVatIncluded = (totalVatIncluded * vouncherOut) / 100;
        document.getElementById('priceoftotal').innerHTML = totalVatIncluded + '$ with Discount';
    } else {
        document.getElementById('priceoftotal').innerHTML = totalVatIncluded + '$';
    }
}

function UpdateQuantity(id) {
    for (i = 0; i < list.length; i++) {
        if (cartCon.children[i].className === id) {
            ++cartCon.children[i].children[2].children[1].value;
        }
    }
}

function inCQuan(id) {
    ++id.parentElement.children[1].value;
    totalPrice();
    removeSpan();
}

function deCQuan(id) {
    --id.parentElement.children[1].value;
    if (id.parentElement.children[1].value == 0)
        removeCart(id);
    totalPrice();
    removeSpan();
}

function AddnewRow(id, bookImg, bookTitle, bookPrice) {
    if ($('#emptyCar').length > 0) {
        $('#emptyCar').remove();
    }
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
                                                <td class="align-middle" id="cart${id}">
                                                    <a onclick="deCQuan(this)"><button class="btnSub">-</button></a> 
                                                    <input type="numberic" data-target="quantity" onclick="removeSpan()" oninput="totalPrice()" value=1 class="quaninput" style="width:30%; text-align:center;"></input>
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
    removeSpan();
}
var bookList = new Array();

function checkBilling() {
    if ($('input[name="phoneBill"]').val().length !== 10) {
        flash($('input[name="phoneBill"]'));
        $("#cartBt").animate({scrollTop: 0}, 'slow')
    } else if ($('input[name="addrBill"]').val().length < 2) {
        flash($('input[name="addrBill"]'));
        $("#cartBt").animate({scrollTop: 0}, 'slow')
    } else {
        toCheckout();
    }

}


function toCheckout() {
    bookList.length = 0;
    var priceoftotal = document.getElementById("priceoftotal").textContent;
    for (i = 0; i < list.length; i++) {
        bookList.push(list[i] + '-' + cartCon.children[i].children[2].children[1].value);
    }
    $.ajax({
        url: "/BookShop/CheckoutController",
        type: "post",
        traditional: true,
        data: {
            priceoftotal: priceoftotal,
            listbk: bookList
        },
        success: function (data) {
            removeSpan();
            if (data[0] === "B") {
                var id = data.split('-');
                id.pop();
                for (var i in id) {
                    callTheAmountExce(id[i]);
                    totalPrice();
                }
            } else {
                checkOut();
            }
        },
        error: function (xhr) {
            //Do Something to handle error
        }
    });
}

function checkOut() {
    var phone = $('#iPhone').val();
    var addr = $('#iAddr').val();
    var hidPhone = $('#uHidPhone').val();
    $.ajax({
        url: "/BookShop/TotalCheckOutController",
        type: "post",
        traditional: true,
        data: {
            phone: phone,
            addr: addr,
            hidPhone: hidPhone
        },
        success: function (data) {
            var row = document.getElementById('cartBodyHere');
            row.innerHTML = data;
            setTimeout(function () {
                window.location.href = "/BookShop/home";
            }, 2000);
        },
        error: function (xhr) {
            //Do Something to handle error
        }
    });
}

function callTheAmountExce(id) {
    var row = document.getElementById("cart" + id);
    flash(row);
    row.innerHTML += "<span id='notbook'><h6 style='display: -webkit-inline-box; color:red; '>   Not Enought Book</h6></span>";
    $("#cartBt").animate({scrollTop: 0}, 'slow')
}

var flash = function (elements) {
    var opacity = 100;
    var color = "233, 86, 86"; // has to be in this format since we use rgba
    var interval = setInterval(function () {
        opacity -= 3;
        if (opacity <= 0)
            clearInterval(interval);
        $(elements).css({background: "rgba(" + color + ", " + opacity / 100 + ")"});
    }, 30);
};

function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
}