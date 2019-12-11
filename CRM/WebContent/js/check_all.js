/**
 * Created by zhaojing on 2016/4/19.
 */
var cl = document.getElementById("check_all");
var c = document.getElementById("check");
var co = document.getElementsByName("check_one");
function check_box_all() {
    for (var n = 1; n < co.length; n++) {
        co[n].checked = c.checked;
    }
}
function check_ont() {
    for (var n = 1; n < co.length; n++) {
        if (co[n].checked == true) {
            co[n].checked = false;
        } else {
            co[n].checked = true;
        }
    }
}
function check_all() {
    for (var n = 1; n < co.length; n++) {
        if (co[n].checked == false) {
            co[n].checked = true;
        }
    }
}
/*ÅúÁ¿É¾³ý*/function del_all(){
    var get_id="";
    for(var n = 1; n < co.length; n++){
        if(co[n].checked==true){
          get_id+=co[n].value+",";

        }

    }


}
/*²éÑ¯*/
var username=document.getElementById("get_name");
function get_found(){
   var un=username.value;




}


