var thedata = new Date();
var nowyear = thedata.getFullYear();
var nowyear0 = nowyear - 1;
var nowmouth = thedata.getMonth();
if (nowmouth == 0) {
    nowyear--;
    nowmouth = 11;
}
var startdatas = [];
var startitem = [];
var enditem = [];
var startdata1;
var startdata2;
var enddatas = [];
var datacontent = document.getElementById("datacontent");

function datainit() {
    var str = "";
    str += "<div class='data-body'>";
    str += "<div class='data-header'>";
    if (isrunnian(nowyear) && nowmouth == 2) {

        str += "<span id='selected'><span id='syear'>" + nowyear0 + "-</span><span id='smouth'>" + nowmouth + "-</span>01~<span id='eyear'>" + nowyear + "-</span><span id='emouth'>" + nowmouth + "-29</span></span><button class='data-ok' onclick='getfulldata()'>确定</button>";
    }
    if (!isrunnian(nowyear) && nowmouth == 2) {
        str += "<span id='selected'><span id='syear'>" + nowyear0 + "-</span><span id='smouth'>" + nowmouth + "-</span>01~<span id='eyear'>" + nowyear + "-</span><span id='emouth'>" + nowmouth + "-28</span></span><button class='data-ok' onclick='getfulldata()'>确定</button>";
    }
    if (nowmouth == 1 || nowmouth == 3 || nowmouth == 5 || nowmouth == 7 || nowmouth == 8 || nowmouth == 10 || nowmouth == 12) {
        str += "<span id='selected'><span id='syear'>" + nowyear0 + "-</span><span id='smouth'>" + nowmouth + "-</span>01~<span id='eyear'>" + nowyear + "-</span><span id='emouth'>" + nowmouth + "-31</span></span><button class='data-ok' onclick='getfulldata()'>确定</button>";
    }
    if (nowmouth == 4 || nowmouth == 6 || nowmouth == 9 || nowmouth == 11) {
        str += "<span id='selected'><span id='syear'>" + nowyear0 + "-</span><span id='smouth'>" + nowmouth + "-</span>01~<span id='eyear'>" + nowyear + "-</span><span id='emouth'>" + nowmouth + "-30</span></span><button class='data-ok' onclick='getfulldata()'>确定</button>";
    }
    str += "</div>";
    str += "<div class='data-content'>";
    str += "<span class='data-start'>";
    str += "<b id='startgodown' onclick='startgodown()' style='float:left;'><</b><b id='startyear'>" + (nowyear - 1) + "</b><b id='startgoup' onclick='startgoup()' style='float:right;'>></b><br/>"
    for (var i = 1; i < 13; i++) {
        if (i == nowmouth) {
            str += "<span class='dataactive' onclick='getstartdata(this)'>" + i + "</span>";
        } else if (i > nowmouth) {
            str += "<span style='color:white;' onclick='getstartdata(this)'>" + i + "</span>";
        } else {
            str += "<span onclick='getstartdata(this)'>" + i + "</span>";
        }
    }
    str += "</span>";
    str += "<span class='data-end'>";
    str += "<b id='endgodown' onclick='endgodown()' style='float:left;'><</b><b id='endyear'>" + nowyear + "</b><b id='endgoup' onclick='endgoup()' style='float:right;'>></b><br/>"
    for (var i = 1; i < 13; i++) {
        if (i < nowmouth) {
            str += "<span style='color:white;' onclick='getenddata(this)'>" + i + "</span>";
        } else if (i == nowmouth) {
            str += "<span class='dataactive' onclick='getenddata(this)'>" + i + "</span>";
        } else {
            str += "<span onclick='getenddata(this)'>" + i + "</span>";
        }
    }

    str += "</span>";
    str += "</div>";
    str += "</div>";
    datacontent.style.display = "block";
    datacontent.innerHTML = str;
    enddatas = (".data-end span[class='dataactive']");
    startdata2 = enddatas[0];
    startitem = $(".data-start span");
    enditem = $(".data-end span");
};

function getstartdata(it) {
    startdatas = $(".data-start span[class='dataactive']");
    enddatas = $(".data-end span[class='dataactive']");
    var startyear = parseInt(document.getElementById("startyear").innerHTML);
    var endyear = parseInt(document.getElementById("endyear").innerHTML);
    if (enddatas.length == 0 && startdatas.length == 2) {
        //alert("start2");
        for (var i = 0; i < 12; i++) {
            startitem[i].style.color = "#555";
        }
        startdata1.classList.remove("dataactive");
        startdata1 = startdata2;
        startdata2 = it;
        it.className = "dataactive";
        startyear = parseInt(document.getElementById("startyear").innerHTML);
        endyear = startyear;
        number1 = parseInt(startdata1.innerHTML);
        number2 = parseInt(startdata2.innerHTML);
        if (number1 >= number2) {
            for (var i = number2 - 1; i < number1; i++) {
                startitem[i].style.color = "white";
            }
            fullselected(startyear, number2, endyear, number1);
        } else {
            for (var i = number1 - 1; i < number2; i++) {
                startitem[i].style.color = "white";
            }
            fullselected(startyear, number1, endyear, number2);
        }
    }
    if (enddatas.length == 2 && startdatas.length == 0) {
        for (var i = 0; i < 12; i++) {
            enditem[i].style.color = "#555";
        }
        startyear = parseInt(document.getElementById("startyear").innerHTML);
        endyear = parseInt(document.getElementById("endyear").innerHTML);
        if (startyear < endyear) {
            startdata1.classList.remove("dataactive");
            startdata1 = startdata2;
            it.className = "dataactive";
            startdata2 = it;
            number1 = parseInt(startdata1.innerHTML);
            number2 = parseInt(startdata2.innerHTML);
            for (var i = number2 - 1; i < startitem.length; i++) {
                startitem[i].style.color = "white";
            }
            for (var j = 0; j < number1 - 1; j++) {
                enditem[j].style.color = "white";
            }
            fullselected(startyear, number2, endyear, number1);
        } else {
            startdata1.remove("dataactive");
            startdata2.remove("dataactive");
            it.className = "dataactive";
            startdata1 = it;
            startdata2 = it;
            startyear = parseInt(document.getElementById("startyear").innerHTML);
            endyear = startyear;
            number1 = parseInt(startdata1.innerHTML);
            number2 = parseInt(startdata2.innerHTML);
            fullselected(startyear, number1, endyear, number2);
        }
    }
    if (enddatas.length == 1 && startdatas.length == 1) {
        //alert("同为1");
        for (var i = 0; i < 12; i++) {
            enditem[i].style.color = "#555";
        }
        for (var i = 0; i < 12; i++) {
            startitem[i].style.color = "#555";
        }
        startyear = parseInt(document.getElementById("startyear").innerHTML);
        endyear = parseInt(document.getElementById("endyear").innerHTML);
        //alert(startyear+"/"+endyear);
        if (startyear < endyear) {
            //console.log("小到大");
            startdata1 = startdatas[0];
            startdata2 = enddatas[0];
            it.className = "dataactive";
            startdata1.classList.remove("dataactive");
            startdata1 = it;
            number1 = parseInt(startdata1.innerHTML);
            number2 = parseInt(startdata2.innerHTML);
            console.log(number1 + "/" + number2);
            for (var i = number1 - 1; i < startitem.length; i++) {
                startitem[i].style.color = "white";
            }
            for (var j = 0; j < number2 - 1; j++) {
                enditem[j].style.color = "white";
            }
            fullselected(startyear, number1, endyear, number2);
        } else {
            startdata1 = startdatas[0];
            startdata2 = enddatas[0];
            endyear = startyear;
            startdata1.classList.remove("dataactive");
            it.className = "dataactive";
            startdata1 = it;
            number1 = parseInt(startdata1.innerHTML);
            number2 = parseInt(startdata2.innerHTML);
            for (var i = 11; i <= number1 - 1; i--) {
                startitem[i].style.color = "white";
            }
            for (var i = 0; i < number2; i++) {
                enditem[i].style.color = "white";
            }
            fullselected(startyear, number2, endyear, number1);

        }

    }
}

function fullselected(number1, number2, number3, number4) {
    if (isrunnian(number1) && number4 == 2) {
        document.getElementById("syear").innerHTML = number1 + "-";
        document.getElementById("smouth").innerHTML = number2 + "-";
        document.getElementById("eyear").innerHTML = number3 + "-";
        document.getElementById("emouth").innerHTML = number4 + "-29";
    }
    if (!isrunnian(endyear) && number4 == 2) {
        document.getElementById("syear").innerHTML = number1 + "-";
        document.getElementById("smouth").innerHTML = number2 + "-";
        document.getElementById("eyear").innerHTML = number3 + "-";
        document.getElementById("emouth").innerHTML = number4 + "-28";
    }
    if (number4 == 1 || number4 == 3 || number4 == 4 || number4 == 7 || number4 == 8 || number4 == 10 || number4 == 12) {
        document.getElementById("syear").innerHTML = number1 + "-";
        document.getElementById("smouth").innerHTML = number2 + "-";
        document.getElementById("eyear").innerHTML = number3 + "-";
        document.getElementById("emouth").innerHTML = number4 + "-31";
    }
    if (number4 == 4 || number4 == 6 || number4 == 9 || number4 == 11) {
        document.getElementById("syear").innerHTML = number1 + "-";
        document.getElementById("smouth").innerHTML = number2 + "-";
        document.getElementById("eyear").innerHTML = number3 + "-";
        document.getElementById("emouth").innerHTML = number4 + "-30";
    }
}

function getenddata(it) {
    startdatas = $(".data-start span[class='dataactive']");
    enddatas = $(".data-end span[class='dataactive']");
    var startyear = parseInt(document.getElementById("startyear").innerHTML);
    var endyear = parseInt(document.getElementById("endyear").innerHTML);
    var number1;
    var number2;
    if (enddatas.length == 0 && startdatas.length == 2) {
        if (startyear < endyear) {
            startdata1.classList.remove("dataactive");
            startdata1 = startdata2;
            it.className = "dataactive";
            startdata2 = it;
            number1 = parseInt(startdata1.innerHTML);
            number2 = parseInt(startdata2.innerHTML);
            for (var i = number1 - 1; i < startitem.length; i++) {
                startitem[i].style.color = "white";
            }
            for (var j = 0; j < number2 - 1; j++) {
                enditem[j].style.color = "white";
            }
            fullselected(startyear, number1, endyear, number2);
        } else {
            startdata1 = startdatas[0];
            startdata2 = startdatas[1];
            startdata1.classList.remove("dataactive");
            startdata2.classList.remove("dataactive");
            startyear = (document.getElementById("endyear").innerHTML);
            endyear = startyear;
            it.className = "dataactive";
            startdata1 = it;
            startdata2 = it;
            number1 = parseInt(startdata1.innerHTML);
            number2 = parseInt(startdata2.innerHTML);
            fullselected(startyear, number1, endyear, number2);
        }
    }
    if (enddatas.length == 2 && startdatas.length == 0) {
        for (var i = 0; i < 12; i++) {
            enditem[i].style.color = "#555";
        }
        startyear = parseInt(document.getElementById("startyear").innerHTML);
        endyear = parseInt(document.getElementById("endyear").innerHTML);
        startdata1.classList.remove("dataactive");
        it.className = "dataactive";
        startdata2 = it;
        number1 = parseInt(startdata1.innerHTML);
        number2 = parseInt(startdata2.innerHTML);
        if (number1 >= number2) {
            for (var i = number2 - 1; i < number1; i++) {
                enditem[i].style.color = "white";
            }
            fullselected(startyear, number2, endyear, number1);
        } else {
            for (var i = number1 - 1; i < number2; i++) {
                enditem[i].style.color = "white";
            }
            fullselected(startyear, number1, endyear, number2);
        }
    }
    if (enddatas.length == 1 && startdatas.length == 1) {
        for (var i = 0; i < 12; i++) {
            enditem[i].style.color = "#555";
        }
        startyear = parseInt(document.getElementById("startyear").innerHTML);
        endyear = parseInt(document.getElementById("endyear").innerHTML);
        console.log(startyear + "/" + endyear);
        if (startyear < endyear) {
            console.log("小到大");
            startdata1 = startdatas[0];
            startdata2 = enddatas[0];
            startdata2.classList.remove("dataactive");
            it.className = "dataactive";
            startdata2 = it;
            number1 = parseInt(startdata1.innerHTML);
            number2 = parseInt(startdata2.innerHTML);
            console.log(number1 + "/" + number2);
            for (var j = 0; j < number2 - 1; j++) {
                enditem[j].style.color = "white";
            }
            fullselected(startyear, number1, endyear, number2);
        } else {
            startdata1 = startdatas[0];
            startdata2 = enddatas[0];
            startdata1.classList.remove("dataactive");
            it.className = "dataactive";
            startdata1 = it;
            endyear = parseInt(document.getElementById("endyear").innerHTML);
            startyear = endyear;
            number1 = parseInt(startdata1.innerHTML);
            number2 = parseInt(startdata2.innerHTML);
            if (number1 >= number2) {
                for (var i = number2 - 1; i < number1; i++) {
                    enditem[i].style.color = "white";
                }
                fullselected(startyear, number2, endyear, number1);
            } else {
                for (var i = number1 - 1; i < number2; i++) {
                    enditem[i].style.color = "white";
                }
                fullselected(startyear, number1, endyear, number2);
            }
        }

    }
}

function getfulldata() {
    var sy = document.getElementById("syear").innerHTML;
    var sm = document.getElementById("smouth").innerHTML;
    var ey = document.getElementById("eyear").innerHTML;
    var em = document.getElementById("emouth").innerHTML;
    if (sm.length == 2) {
        var t = "0" + sm;
        sm = t;
    }
    if (em.length == 4) {
        var t = "0" + em;
        em = t;
    }
    document.getElementById("mydata").value = sy + sm + "01~" + ey + em;
    datacontent.style.display = "none";
}

function isrunnian(number) {
    if (number % 100 == 0) {
        if (number % 400 == 0) {
            return true;
        } else {
            return false;
        }
    } else {
        if (number % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }
}

function startgodown() {
    var startyear = parseInt(document.getElementById("startyear").innerHTML);
    document.getElementById("startyear").innerHTML = --startyear;
}

function startgoup() {
    var startyear = parseInt(document.getElementById("startyear").innerHTML);
    if (startyear == nowyear) {
        alert("已是最大可选择年份！");
    } else {
        document.getElementById("startyear").innerHTML = ++startyear;
    }
}

function endgodown() {
    var endyear = parseInt(document.getElementById("endyear").innerHTML);
    document.getElementById("endyear").innerHTML = --endyear;
}

function endgoup() {
    var endyear = parseInt(document.getElementById("endyear").innerHTML);
    if (endyear == nowyear) {
        alert("已是最大可选择年份！");
    } else {
        document.getElementById("endyear").innerHTML = ++endyear;
    }
}
