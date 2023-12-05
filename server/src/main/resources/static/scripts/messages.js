document.addEventListener("DOMContentLoaded", function() {
    let previousCount = getRecordCount();

    function getRecordCount() {
        const request = new XMLHttpRequest();
        request.open("GET", "/count", false);
        request.send();
        return parseInt(request.responseText);
    }

    function checkForUpdates() {
        const currentCount = getRecordCount();

        if (currentCount !== previousCount) {
            location.reload();
        }
        previousCount = currentCount;
    }

    setInterval(checkForUpdates, 5000);
});
