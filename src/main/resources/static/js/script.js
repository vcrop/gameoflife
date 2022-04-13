function onStart(arg) {

  document.addEventListener("DOMContentLoaded", () => {

    let obj = JSON.parse(arg);
    let size = obj.size;
    let matrix = obj.array;

    let canv = document.createElement('canvas');
    let ctx = canv.getContext("2d");

    canv.id = 'canvasId';
    canv.width = size * 20;
    canv.height = size * 20;

    document.body.appendChild(canv);

    draw(ctx, size, matrix);

    setInterval(function() {

        let request = new XMLHttpRequest();
        request.open('POST', '/', false);
        request.send();

        draw(ctx, size, JSON.parse(request.responseText));

    }, 1000);

  });

}

function draw(ctx, size, matrix) {

    let death = [];
    let born = [];

    for (let row = 0; row < size; row++) {
        for (let col = 0; col < size; col++) {
            if (matrix[row][col] == 2) born.push([col, row]);
            if (matrix[row][col] == 1) death.push([col, row]);
        }
    }

    let color = 255;

    let timerId = setInterval(function() {
        ctx.fillStyle = "rgba(" + color + ", 255, " + color + ", 1.0)";
        born.forEach(function(item, i, arr) {
            ctx.fillRect(item[0] * 20, item[1] * 20, 20, 20);
        });
        ctx.fillStyle = "rgba(" + (255 - color) + ", 255, " + (255 - color) + ", 1.0)";
                death.forEach(function(item, i, arr) {
                    ctx.fillRect(item[0] * 20, item[1] * 20, 20, 20);
                });
        color = color - 10;
    },
    20);

    setTimeout(() => { clearInterval(timerId);}, 500);

}
