<script>
    import ModalWindow from "../components/ModalWindow.svelte";

    export let product;
    let showCardPreview = false;
    let purchase = false;

    function addToPurchase(e) {
        const ls = localStorage['cart'] ? JSON.parse(localStorage['cart']) : [];
        ls.push(product);
        localStorage['cart'] = JSON.stringify(ls);
    }

</script>

{#if showCardPreview}
    <ModalWindow on:close="{() => showCardPreview = false}">
        <div class="card-name" slot="header">{product.title}</div>
        <div class="card-body">
            <!-- svelte-ignore a11y-img-redundant-alt -->
            <div class="card-image"><img src={product.img} alt="tovar image"/></div>
            <div class="card-description">
                <div class="addToCartButton">Добавить в корзину</div>
                <div class="card-text">{product.description}</div>
            </div>
        </div>
    </ModalWindow>
{/if}

<div id={product.id} class="card" on:mouseenter={() => purchase = true} on:mouseleave={() => purchase = false}>
    <div on:click={() => showCardPreview = true}>
        <!-- svelte-ignore a11y-img-redundant-alt -->
        <div class="image-conrainer"><img src={product.img} alt="tovar image"/></div>
        <div class="tovar-info">
            <div class="tovar-price">{product.price.substring(0, product.price.length - 3)}</div>
            <div class="tovar-name">{product.title}</div>
        </div>
    </div>
    {#if purchase}
        <div class="purchase-container">
            <button type="button" class="purchase" on:click={addToPurchase}>В корзину</button>
        </div>
    {/if}
</div>

<style>

    .card {
        box-sizing: border-box;
        position: relative;
        width: 16%;
        max-height: 320px;
        padding: 8px;
        margin: 8px;
        border-radius: 15px;
        cursor: pointer;
    }

    .card:hover {
        box-shadow: 0 0 20px rgb(0 0 0 / 10%);
    }

    .tovar-name {
        color: #999;
        margin-bottom: 8px;
    }

    .tovar-price {
        font-size: 18px;
        font-weight: bold;
        margin-bottom: 5px;
    }

    .image-conrainer {
        position: relative;
        display: flex;
        justify-content: center;
        margin-bottom: 5px;
    }

    .image-conrainer > img {
        width: 100%;
        z-index: 1;
        border-radius: 5px;
    }

    .addToCartButton {
        margin-top: 15px;
        cursor: pointer;
        margin-bottom: 15px;
    }

    .card-name {
        margin-bottom: 10px;
    }

    .card-body {
        display: flex;
    }

    .card-image {
        width: 60%;
        margin-right: 15px;
    }

    .card-image > img {
        width: 100%;
        border-radius: 5px;
    }

    .card-description {
        width: 30%;
    }

    .purchase-container {
        position: absolute;
        width: 100%;
        height: 3.3rem;
        margin-top: 8px;
        left: 0;
        display: flex;
        justify-content: flex-start;
        bottom: -3rem;
        align-items: end;
        padding: 0 0 8px 0;
        background-color: #fff;
        border-radius: 0 0 5px 5px;
        box-shadow: 0 5px 5px rgb(0 0 0 / 10%);

        z-index: 1000;
    }

    .purchase {
        padding: 4px 16px;
        margin-left: 8px;

        background: #10ad44;
        color: #fff;

        height: 28px;
        width: 8rem;

        font-family: 'Roboto', sans-serif;
        font-size: 14px;
        font-weight: bold;

        border-radius: 8px;
        cursor: pointer;
    }

    .purchase:hover {
        background: #086e28;
    }

</style>