var magnetParams = document.getElementById("magnetParams");
magnetParams.style.display = 'none';

var magnetCheckbox = document.getElementById("addHoleForMagnet");
magnetCheckbox.onchange = function() {
    if (magnetCheckbox.checked) {
        magnetParams.style.display = 'block';
    } else {
        magnetParams.style.display = 'none';
    }
}

var hollowParams = document.getElementById("hollowParams");
hollowParams.style.display = 'none';

var hollowCheckbox = document.getElementById("makeHollow");
hollowCheckbox.onchange = function() {
    if (hollowCheckbox.checked) {
        hollowParams.style.display = 'block';
    } else {
        hollowParams.style.display = 'none';
    }
}