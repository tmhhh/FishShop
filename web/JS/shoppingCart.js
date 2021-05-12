//// Remove Items From Cart
    //$("a.remove").click(function () {
    //  event.preventDefault();
    //  $(this).parent().parent().parent().hide(400);
    //});
    //
    //// Just for testing, show all items
    //$("a.btn.continue").click(function () {
    //  $("li.items").show(400);
    //});

    //var listUpdate = document.querySelectorAll(".form-update");
    //listUpdate.forEach((e) => {
    //  e.onsubmit = () => {
    //    return false;
    //  };
    //});

    var inputUpdate = document.querySelectorAll(".item-quantity");

    inputUpdate.forEach((e) => {
      e.setAttribute("then", e.value);
      e.onblur = () => {
        if (e.value !== e.getAttribute("then")){
          e.setAttribute("then", e.value);
          e.parentElement.parentElement.submit();
      }
      };
    });
    
    
    var btnContinue = document.querySelector(".btn-checkout");
    btnContinue.onclick=()=>{
        btnContinue.parentElement.submit();
    }
