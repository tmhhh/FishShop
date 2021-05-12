magnify("myimage", 2);

function Plus() {
    var i = document.getElementById("quantity");
    i.value++;
}
function Minus() {
    var i = document.getElementById("quantity");
    if (i.value > 0)
        i.value--;
}

function magnify(imgID, zoom) {
    var img, glass, w, h, bw;
    img = document.getElementById(imgID);

    /* Create magnifier glass: */
    glass = document.createElement("DIV");
    glass.setAttribute("class", "img-magnifier-glass");
    glass.setAttribute("id", "glass");
    /* Insert magnifier glass: */
    img.parentElement.insertBefore(glass, img);

    /* Set background properties for the magnifier glass: */
    glass.style.backgroundImage = "url('" + img.src + "')";
    glass.style.backgroundRepeat = "no-repeat";
    glass.style.backgroundSize =
            img.width * zoom + "px " + img.height * zoom + "px";
    bw = 3;
    w = glass.offsetWidth / 2;
    h = glass.offsetHeight / 2;

    /* Execute a function when someone moves the magnifier glass over the image: */
    glass.addEventListener("mousemove", moveMagnifier);
    img.addEventListener("mousemove", moveMagnifier);

    /*and also for touch screens:*/
    glass.addEventListener("touchmove", moveMagnifier);
    img.addEventListener("touchmove", moveMagnifier);
    function moveMagnifier(e) {
        var pos, x, y;
        /* Prevent any other actions that may occur when moving over the image */
        e.preventDefault();
        /* Get the cursor's x and y positions: */
        pos = getCursorPos(e);
        x = pos.x;
        y = pos.y;
        /* Prevent the magnifier glass from being positioned outside the image: */
        if (x > img.width - w / zoom) {
            x = img.width - w / zoom;
        } else if (x < w / zoom) {
            x = w / zoom;
        } else if (y > img.height - h / zoom) {
            y = img.height - h / zoom;
        } else if (y < h / zoom) {
            y = h / zoom;
        }

        /* Set the position of the magnifier glass: */
        glass.style.left = x - w + "px";
        glass.style.top = y - h + "px";
        /* Display what the magnifier glass "sees": */
        glass.style.backgroundPosition =
                "-" + (x * zoom - w + bw) + "px -" + (y * zoom - h + bw) + "px";
    }

    function getCursorPos(e) {
        var a,
                x = 0,
                y = 0;
        e = e || window.event;
        /* Get the x and y positions of the image: */
        a = img.getBoundingClientRect();
        /* Calculate the cursor's x and y coordinates, relative to the image: */
        x = e.pageX - a.left;
        y = e.pageY - a.top;
        /* Consider any page scrolling: */
        x = x - window.pageXOffset;
        y = y - window.pageYOffset;
        return {x: x, y: y};
    }
}
// function ChangeImg() {
//   var a = document.getElementById("myimage");
//   a.innerHTML = "<img src='img/1.png' >";
//   // +image+".png'>";
// }

function MoveOut() {
    var glass = document.getElementsByClassName("img-magnifier-glass")[0];
    glass.style.display = "none";
}
function MoveOn() {
    var glass = document.getElementsByClassName("img-magnifier-glass")[0];
    glass.style.display = "block";
}

function Reply(i) {
    var replyField = document.getElementsByClassName("comment-reply")[i];
    var replyButton = document.getElementsByClassName("reply-button")[i];

    if (replyField.style.display === "none")
    {
        replyField.style.display = "block";
        replyButton.innerHTML = "X";
        replyButton.style.color = "red";

    }
    else
    {
        replyField.style.display = "none";
        replyButton.innerHTML = "Reply";
        replyButton.style.color = "#0277BD";

    }

}

