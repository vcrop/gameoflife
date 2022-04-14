function onStart(arg) {

    document.addEventListener("DOMContentLoaded", () => {

        init(JSON.parse(arg));

        setInterval(timer, 1000);

    })

}

function init(obj) {

    let container = document.getElementById('container');

    container.style.gridTemplateRows.repeat(obj.size);
    container.style.gridTemplateColumns.repeat(obj.size);

    for (let row = 0; row < obj.size; row++)
        for (let col = 0; col < obj.size; col++) {

            let div = document.createElement('div');

            div.id = 'box' + col + '_' + row;
            div.style.gridArea = (row + 1) + '/' + (col + 1) + '/' + (row + 2) + '/' + (col + 2);

            container.appendChild(div);
        }

    draw(obj);

}

function draw(obj) {

    for (let row = 0; row < obj.size; row++)
        for (let col = 0; col < obj.size; col++)
            document.getElementById('box' + col + '_' + row)
                .style
                .animation = '1s ease-in-out 0s 1 normal forwards running ' + ['empty', 'death', 'born', 'live'][obj.array[col][row]];
}

function timer() {

    let request = new XMLHttpRequest();
    request.open('POST', '/', false);
    request.send();

    draw(JSON.parse(request.responseText));

}