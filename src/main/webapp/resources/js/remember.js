/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function remember(){
	var data = document.getElementById("accountId").getAttribute('account');
	$('div.form-group INPUT[name="username"]').val(data);
	$('div.form-group INPUT[name="password"]').val(123);
	
};



