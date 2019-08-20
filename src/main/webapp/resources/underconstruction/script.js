$(document).ready(function() {

    var done = 70;
    var todo = 30;

    Morris.Donut({
        element: 'donut',
        resize: true,
        data: [
          {label: 'Done', value: done },
          {label: 'Todo', value: todo }
        ],
        formatter: function (y) { return y + "%" }
    });

    ts = getTimestamp(enddate);

    function getTimestamp(str) {
    var d = str.match(/\d+/g);
    return +new Date(d[0], d[1] - 1, d[2], d[3], d[4], d[5]);
    }

    function tick(){

            var days	= 24*60*60,
                hours	= 60*60,
                minutes	= 60;

            // Time left
            left = Math.floor((ts - (new Date())) / 1000);

            if(left < 0){
                left = 0;
            }

            // Number of days left
            d = Math.floor(left / days);
            left -= d*days;
            $('#days').html(d);

            // Number of hours left
            h = Math.floor(left / hours);
            left -= h*hours;
            $('#hours').html(h);

            // Number of minutes left
            m = Math.floor(left / minutes);
            left -= m*minutes;
            $('#mins').html(m);

            // Number of seconds left
            s = left;
            $('#secs').html(s);

            setTimeout(tick, 1000);
    };

    tick();
});