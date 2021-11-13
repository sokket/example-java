<script lang="ts">
    import CartListItem from "../components/CartListItem.svelte";
    import {writable} from 'svelte/store';

    let json = JSON.parse(localStorage['cart'])

    let productsCart = writable(json.map(e => {
        e['selected'] = true;
        e['count'] = 1;
        e['price'] = Number(e['price'].substr(0, e['price'].length - 4))
        return e
    }));

    let sum = writable(0);
    let count = writable(0);



    let allSelected = writable(true);

    update()

    let regions = [
        {id: 1, name: "Волжск", price: 100 * 20},
        {id: 2, name: "Козьмодемьянск", price: 121 * 20},
        {id: 3, name: "Звенигово", price: 93 * 20},
        {id: 4, name: "Килемары", price: 81 * 20},
        {id: 5, name: "Куженер", price: 73 * 20},
        {id: 6, name: "Мари Турек", price: 124 * 20},
        {id: 7, name: "Морки", price: 112 * 20},
        {id: 8, name: "Новый Торъял", price: 80 * 20},
        {id: 9, name: "Оршанка", price: 32 * 20},
        {id: 10, name: "Параньга", price: 111 * 20},
        {id: 11, name: "Сернур", price: 88 * 20},
        {id: 12, name: "Советский", price: 42 * 20},
        {id: 13, name: "Юрино", price: 201 * 20},

    ];
    let selected = regions[0];

    function update() {
        $sum = 0;
        $count = 0;
        $productsCart.forEach(e => {
            if (e.selected) {
                $sum += e.price * e.count;
                $count += Number(e.count);
            }
        });
        $allSelected = $productsCart.filter(e => !e.selected).length === 0;
    }

    function selectAll() {
        if ($allSelected) {
            $allSelected = false;
            for (let i = 0; i < $productsCart.length; i++) {
                if ($productsCart[i].selected) {
                    $productsCart[i].selected = false;
                    $productsCart[i] = $productsCart[i];
                }
            }
        } else {
            $allSelected = true;
            for (let i = 0; i < $productsCart.length; i++) {
                if (!$productsCart[i].selected) {
                    $productsCart[i].selected = true;
                    $productsCart[i] = $productsCart[i];
                }
            }
        }
    }

</script>

<main id="mainContainer">
    <div class="mainContainer-inner">
        <div class="wrapper">
            <div class="wrapper-container">
                <div class="wrapper-container-title">
                    <h1 class="title">Корзина</h1>
                </div>
                <div class="wrapper-container-card">
                    <div class="products-card">
                        <div class="card-selector">
                            <div class="checkbox-holder" on:click={selectAll}>
                                <input type="checkbox" bind:checked={$allSelected}> Выбрать все
                            </div>
                            <div class="selector-deleteAll" on:click={() => {alert("Не работает в MVP")}}>Удалить выбраные</div>
                        </div>
                        <div class="products-card-inner">
                            <div class="card-order-delivery">
                                <div class="card-order-delivery-inner">
                                    <div class="order-delivery-inner-title">
                                        Доставка
                                    </div>
                                    <div class="order-delivery-inner-price">
                                        <select class="delivery-region" bind:value={selected}>
                                            {#each regions as region}
                                                <option value={region}>
                                                    {region.name}
                                                </option>
                                            {/each}
                                        </select>
                                        <div class="delivery-region-price">
                                            Цена: {(selected ? selected.price : 0)}
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-list">
                                {#each $productsCart as product}
                                    <CartListItem {product} on:change={update}/>
                                {/each}
                            </div>
                        </div>
                    </div>
                    <div class="products-order">
                        <div class="products-order-container">
                            <div class="make-order">
                                <button type="button" on:click={() => alert("Оформление не доступно в MVP")}>Перейти к оформлению</button>
                            </div>
                            <div class="order-info">
                                <div class="order-info-title">
                                    <div class="title-text">Ваша корзина</div>
                                    <div class="order-count">{$count} товара</div>
                                </div>
                                <div class="order-cart-price">
                                    <div class="cart-title">Товары ({$count})</div>
                                    <div class="cart-price">{$sum}</div>
                                </div>
                                <div class="order-delivery-price">
                                    <div class="delivery-title">Доставка</div>
                                    <div class="delivery-price">{selected.price}</div>
                                </div>
                                <div class="order-info-summary">
                                    <div class="summary-title">Общая стоимость</div>
                                    <div class="summary-price">{$sum + selected.price}</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<style>

    input, button, select {
        margin: 0;
    }

    #mainContainer {
        width: 100vw;
        min-height: calc(100vh - 4rem - 1px);
        height: calc(100vh - 4rem - 1px);
        overflow-y: auto;
        overflow-x: hidden;
        background: #f7f8f9;
        z-index: 1;
    }

    .mainContainer-inner {
        width: 100%;
        max-width: 1472px;
        height: 100%;

        padding: 2rem;
        margin: 0 auto 0;
        box-sizing: border-box;
    }

    .wrapper {
        height: 100%;
        width: 100%;
    }

    .wrapper-container {
        display: flex;
        flex-direction: column;
    }

    .wrapper-container-card {
        width: 100%;
        min-height: 372px;

        display: flex;
        flex-direction: row;
    }

    .products-card {
        display: flex;
        flex-direction: column;
        flex-grow: 1;
    }

    .card-selector {
        height: 48px;

        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        align-items: center;

        padding: 0 16px;

        font-family: 'Roboto', sans-serif;
        font-size: 14px;

        background: #fff;
    }

    .products-card-inner {
        padding: 16px;
        box-sizing: border-box;

        border-top: 1px solid rgba(0, 26, 52, .16);;
        border-radius: 2px;

        background: #fff;
    }

    .card-order-delivery {
        padding: 16px;
        box-sizing: border-box;
        background: #eff3f6;
        border-radius: 2px;
    }

    .card-order-delivery-inner {
        height: 29px;

        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
    }

    .order-delivery-inner-title {
        font-size: 14px;
        font-weight: 700;
    }

    .order-delivery-inner-price {
        display: flex;
        flex-direction: row;
        align-items: center;
        font-size: 14px;
        font-weight: 700;
    }


    .delivery-region {
        margin-right: 16px;
    }

    .delivery-region-price {
        width: 96px;

        display: flex;
        justify-content: center;
        align-items: center;
    }

    .card-list {
        padding-top: 16px;
    }

    .checkbox-holder {
        margin-right: 24px;
        align-items: center;
        cursor: pointer;
        display: flex;
        font-size: 14px;
        line-height: 1.29em;
    }

    .checkbox-holder input {
        margin-right: 10px;
    }

    .selector-deleteAll {
        color: #b40021;
        align-items: center;
        cursor: pointer;
        display: flex;
        font-size: 14px;
        line-height: 1.29em;
    }

    .products-order {
        height: calc(100% - 24px);
        width: 30%;

        margin: 0 0 24px 24px;

        flex-grow: 1;
        background: white;
    }

    .products-order-container {
        height: 100%;
        width: 100%;

        display: flex;
        flex-direction: column;
    }

    .make-order {
        height: 35%;
        width: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 24px;
        box-sizing: border-box;
    }

    .make-order button {
        width: 80%;
        height: 56px;
        background: #10ad44;
        border-radius: 6px;

        font-family: 'Roboto', sans-serif;
        font-weight: 700;
        font-size: 16px;
        color: #fff;
    }

    .make-order button:hover {
        background: #086e28;
    }

    .order-info {
        height: 65%;
        width: 100%;

        padding: 24px;
        box-sizing: border-box;
        border-top: 1px solid rgba(0, 26, 52, .16);

        display: flex;
        flex-direction: column;
        justify-content: space-between;

        font-family: 'Roboto', sans-serif;
        color: #001a34;
    }

    .order-info-title, .order-cart-price, .order-delivery-price, .order-info-summary {
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: space-between;
    }

    .order-info-title {
        padding-bottom: 16px;
    }

    .title-text {
        font-size: 20px;
        font-weight: 700;
        line-height: 1.4em;
    }

    .order-count {

        font-size: 14px;
        line-height: 1.29em;
        text-align: end;
    }

    .cart-title {
        font-size: 14px;
        justify-content: space-between;
        line-height: 18px;
        margin-bottom: 12px;
    }

    .cart-price {
        font-weight: 700;
    }

    .delivery-title {
        font-size: 14px;
        justify-content: space-between;
        line-height: 18px;
        margin-bottom: 12px;
    }

    .delivery-price {
        font-weight: 700;
    }

    .summary-title, .summary-price {
        font-size: 20px;
        font-weight: 700;
        line-height: 1.4em;
    }

    .order-cart-price, .order-delivery-price {
        margin-bottom: 8px;
    }

    .order-info-summary {
        margin-top: 16px;
        padding-top: 16px;

        border-top: 1px solid rgba(0, 26, 52, .16);
    }

</style>