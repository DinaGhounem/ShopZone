var imageLoader;
var dropbox;
$(document).ready(function () {
    imageLoader = document.getElementById('filePhoto');
    imageLoader.addEventListener('change', handleImage, false);
    dropbox = document.getElementById("uploader");
    dropbox.addEventListener("dragenter", dragenter, false);
    dropbox.addEventListener("dragover", dragover, false);
    dropbox.addEventListener("drop", drop, false);


});

function handleImage(e) {
    // Check if file is an image
    var reader = new FileReader();
    var isImg = checkType(e.target.files[0].type);
    if (isImg) {
        reader.readAsDataURL(e.target.files[0]);
        reader.onload = function (event) {

            $('#uploader img').attr('src', event.target.result);
            $("#dragMsg").hide();
        }
    }
    else {
        $("#dragMsg").show();
        $("#dragMsg")[0].innerHTML = "Not an Image!"
        imageLoader.value = "";
        $('#uploader img')[0].src = ""

    }
}

function checkType(fileType) {
    var isImg = false;
    if (fileType == "image/jpeg" || fileType == "image/png" || fileType == "image/gif") {
        isImg = true;
    }
    return isImg;
}

function dragenter(e) {
    e.stopPropagation();
    e.preventDefault();
    $("#uploader").css("background-color", "#CCCCCC");
}

function dragover(e) {
    e.stopPropagation();
    e.preventDefault();
}

function drop(e) {
    $("#uploader").css("background-color", "");
    e.stopPropagation();
    e.preventDefault();
    //you can check e's properties
    //console.log(e);
    var dt = e.dataTransfer;
    var files = dt.files;

    //this code line fires your 'handleImage' function (imageLoader change event)
    imageLoader.files = files;

}