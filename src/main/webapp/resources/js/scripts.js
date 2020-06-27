async function changeOption() {

    // let jsOptions = document.querySelectorAll("#countries > .js");
    let jsOptions = document.querySelectorAll("#countries");
    if (jsOptions.length > 0) {
        jsOptions.forEach(element => element.remove());
    }

    let response = await fetch("/test-system/ajax?command=ADD_COUNTRY", {
        method: 'GET',
    });


    if (response.ok) {
        let json = await response.json();
        document.getElementById("countries").insertAdjacentHTML('beforeend', generateOptionSelect(json));
    } else {
        //todo сообщение
    }
}

function generateOptionSelect(json) {
    let options = "";
    for (let key in json.countries) {
        options = options + "<option class=\"js\" value=" + json.country[key].id + ">" + json.country[key].title + "</option>";
    }
    return options;
}