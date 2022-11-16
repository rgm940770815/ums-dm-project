/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function underlineToCamel(str) {
    var newstr = "";
    if (str) {
        for (var i = 0; i < str.length; i++) {
            var c = str[i];

            if (c === "_") {
                i++;
                c = str[i].toUpperCase();
            }

            newstr += c;
        }
    }

    return newstr;
}