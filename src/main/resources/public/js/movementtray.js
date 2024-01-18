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