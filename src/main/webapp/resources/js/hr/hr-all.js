
$(document).ready(function(){

    $("#hr_save_icon").hide();
    $("#hr_cancel_icon").hide();

    var emp_type;
    $("#hr_edit_icon").click(function(){
        emp_type = $("#hr_input_emp_type").val();
        $(".input_edit_emp_status").css("border","1px solid #9BA0AF");
        $(".input_edit_emp_status").removeAttr("readonly");
        $(this).hide();
        $("#hr_save_icon").show();
        $("#hr_cancel_icon").show();
    });
    $("#hr_save_icon").click(function(){
        $(".input_edit_emp_status").css("border","none");
        $(".input_edit_emp_status").attr("readonly",true);
        $(this).hide();
        $("#hr_edit_icon").show();
        $("#hr_cancel_icon").hide();
    });
    $("#hr_cancel_icon").click(function(){
        $("#hr_input_emp_type").val(emp_type);
        emp_type = "";
        $(".input_edit_emp_status").css("border","none");
        $(".input_edit_emp_status").attr("readonly",true);
        $(this).hide();
        $("#hr_edit_icon").show();
        $("#hr_save_icon").hide();
    });

});