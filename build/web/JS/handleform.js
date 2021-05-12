/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function apUploadform(){
    var uploadform=document.getElementById("uploading");
    var manageform=document.getElementById("managing");
    var orderform=document.getElementById("ordering");
    var blogform=document.getElementById("blog");
    var tagaul=document.getElementById("uploadfunc");
    var tagamng=document.getElementById("managefunc");
    var tagaod=document.getElementById("orderfunc");
    var tagablog=document.getElementById("blogfunc");
    tagaul.style.background="cornflowerblue";
    tagamng.style.background="rgb(173, 173, 173)";
    tagaod.style.background="rgb(173, 173, 173)";
    tagablog.style.background="rgb(173, 173, 173)";
    uploadform.style.display="block";
    manageform.style.display="none";
    orderform.style.display="none";
    blogform.style.display="none";
}
function apManageform(){
    var uploadform=document.getElementById("uploading");
    var manageform=document.getElementById("managing");
    var orderform=document.getElementById("ordering");
    var blogform=document.getElementById("blog");
    var tagaul=document.getElementById("uploadfunc");
    var tagamng=document.getElementById("managefunc");
    var tagaod=document.getElementById("orderfunc");
    var tagablog=document.getElementById("blogfunc");
    tagaul.style.background="rgb(173, 173, 173)";
    tagamng.style.background="cornflowerblue";
    tagaod.style.background="rgb(173, 173, 173)";
    tagablog.style.background="rgb(173, 173, 173)";
    uploadform.style.display="none";
    manageform.style.display="block";
    orderform.style.display="none";
    blogform.style.display="none";
}
function apOrderform(){
    var uploadform=document.getElementById("uploading");
    var manageform=document.getElementById("managing");
    var orderform=document.getElementById("ordering");
    var blogform=document.getElementById("blog");
    var tagaul=document.getElementById("uploadfunc");
    var tagamng=document.getElementById("managefunc");
    var tagaod=document.getElementById("orderfunc");
    var tagablog=document.getElementById("blogfunc")
    tagaul.style.background="rgb(173, 173, 173)";
    tagamng.style.background="rgb(173, 173, 173)";
    tagaod.style.background="cornflowerblue";
    tagablog.style.background="rgb(173, 173, 173)";
    uploadform.style.display="none";
    manageform.style.display="none";
    orderform.style.display="block";
    blogform.style.display="none";
}
function apBlogform(){
    var uploadform=document.getElementById("uploading");
    var manageform=document.getElementById("managing");
    var orderform=document.getElementById("ordering");
    var blogform=document.getElementById("blog");
    var tagaul=document.getElementById("uploadfunc");
    var tagamng=document.getElementById("managefunc");
    var tagaod=document.getElementById("orderfunc");
    var tagablog=document.getElementById("blogfunc")
    tagaul.style.background="rgb(173, 173, 173)";
    tagamng.style.background="rgb(173, 173, 173)";
    tagaod.style.background="rgb(173, 173, 173)";
    tagablog.style.background="cornflowerblue";
    uploadform.style.display="none";
    manageform.style.display="none";
    orderform.style.display="none";
    blogform.style.display="block";
}
function apEditform(){
    var showform=document.getElementById("showform");
    var editform=document.getElementById("editform");
    showform.style.display="none";
    editform.style.display="block";
}