function loadData() {
    $.ajax({
        url: 'ShowProfileServlet',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        success: function (val) {
            var userData = val;
            document.getElementById("input_firstname").value = userData.firstName;
            document.getElementById("input_lastname").value = userData.lastName;
            document.getElementById("input_email").value = userData.email;
            document.getElementById("input_password").value = userData.password;
            document.getElementById("input_address").value = userData.address;
            document.getElementById("input_date").value = userData.birthdate;
            document.getElementById("input_job").value = userData.job;
            document.getElementById("input_creditLimit").value = userData.creditLimit;
            //document.getElementById("input_firstname").value = userData.firstName;
            //document.getElementById("input_firstname").value = userData.firstName;
            
            
       }
    });
}


