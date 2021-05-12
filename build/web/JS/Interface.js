// LEFT COLUMN

$(".list-category").not(".showCategory").slideUp();
$(".category-tag").click(function (event) {
  $(this).next().slideToggle();
  $(this).find(".icon").toggleClass("fa-minus");
  $(this).find(".icon").toggleClass("fa-plus");
});

// END Left Column
$(".btnPurchase").slideUp();
$(".items").hover(function () {
  $(this).find(".btnPurchase").stop(true,true).slideToggle();
});
//  filter mobile
let fiMo = $(".filter-mobile");
fiMo.hide();
$(".btn-soft-mobile").click(function () {
  fiMo.fadeIn("slow");
});
$(".closeFilterMobile").click(function () {
  fiMo.fadeOut("slow");
});

$(window).resize(() => {
  console.log($(window).width());
  if ($(window).width() >  1440) {
    fiMo.fadeOut();
  }
});
// End filter mobile

//item hover

//end item hover

//Price check box
var listPrice = document.querySelectorAll(".listPrice input");
var priceCodes = document.querySelectorAll(".price-range");
listPrice.forEach((price)=>{
    price.onclick = ()=>{
        priceCodes.forEach((priceCode)=>{
            priceCode.value = price.getAttribute("price");
        });

        price.parentElement.parentElement.submit();       
    }
});


//adminTab
function adminTab()
{
  var x = document.getElementById("tab");
  if (x.style.display === "block") {
    x.style.display = "none";
    console.log("tat");
  } else {
    x.style.display = "block";
    console.log("mo");
  }
}

var a = document.querySelectorAll(".select-items-image");
a.forEach((price)=>{
    price.onclick = ()=>{
        price.parentElement.submit();       
    }
});


var listCate = document.querySelectorAll(".formClassify div");
var Cate = document.querySelectorAll(".cate");
listCate.forEach((cate)=>{
  cate.onclick = ()=>{
      Cate.forEach((e)=>{
         e.value = cate.getAttribute("cate");
      })
      cate.parentElement.submit();
  }  
})