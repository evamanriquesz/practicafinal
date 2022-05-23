const getProduct = async () => {
    let request = await fetch("/api/v1/fanstore");
    if (request.status === 200) {
        const data = await request.json();
        compra.getElementById("result").innerHTML = data.toString();

    }
};




