// document.getElementById("demo")
    // .innerHTML = "The text was changed by JavaScript!";

button.onclick = function() {
    clickCount(++iClick, baseInfo);

    let mainWidth = document.documentElement.offsetWidth;
    let mainHeight = document.documentElement.offsetHeight;
    let params = `width=500,height=400,left=${mainWidth/2-500/2},top=${mainHeight/2-400/2}`;
    let popup = window.open('http://192.168.0.101/main', 'Popup', params);

    popup.focus();

    popup.addEventListener('DOMContentLoaded', function() {
        let x = 1,
            y = 1;

        setInterval(() => {
            if (popup.screenX >= window.screen.availWidth - popup.outerWidth) x = -x;
            if (popup.screenX <= 0) x = -x;
            if (popup.screenY >= window.screen.availHeight - popup.outerHeight) y = -y;
            if (popup.screenY <= 0) y = -y;

            popup.moveBy(x, y);
        }, 1);

        setTimeout(() => {
            if (!popup.closed) popup.close()
            else console.log('The pop-up window was closed manually.');
        }, 60e3);
    });

    document.addEventListener('mousedown', popupFocus);
    document.addEventListener('keydown', popupFocus);

    function popupFocus() {
        popup.focus();
    }
};