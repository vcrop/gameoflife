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

    for (let row = 0; row < size; row++) {
        for (let col = 0; col < size; col++) {
            if (matrix[row][col] == 1) ctx.fillStyle = "green";
            if (matrix[row][col] == 0) ctx.fillStyle = "white";
            ctx.fillRect(col * 20, row * 20, 20, 20);
        }
    }

}