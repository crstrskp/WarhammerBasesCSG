// Function to stop the spinner
function stopSpinner() {
    console.log("stopping spinner");
    var spinner = document.getElementById('spinner');
    spinner.style.display = 'none'; // Hide the spinner
}

function startSpinner() {
    console.log("Starting spinner");

    var spinner = document.getElementById('spinner');
    spinner.offsetHeight; // Trigger reflow
    spinner.style.display = 'block'; // Show the spinner
}

// Function to periodically check the process status
function checkProcessStatus() {
    console.log('Checking process status');
    fetch('/process-status')
        .then(response => response.text())
        .then(isComplete => {
            if (isComplete === 'true') {
                stopSpinner();
            } else {
                // If the process is not complete, check again in a second
                // startSpinner();
                setTimeout(checkProcessStatus, 200);
            }
        })
        .catch(error => console.error('Error checking process status:', error));
}

// Start checking the process status
checkProcessStatus();