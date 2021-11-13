<script lang="ts">
    import Icon from "@iconify/svelte";
    import {createEventDispatcher} from 'svelte';

    const dispatch = createEventDispatcher();

    export let product = {
        id: 1,
        selected: true,
        img: 'public/img/Logo.jpg',
        title: 'Капуста',
        description: 'Сочный, вкусны, зеленый',
        price: '465 Руб.',
        count: 2
    };

    $: {
        dispatch('change', product)
    }

</script>

<div class="item">
    <div class="item-checkbox">
        <input type="checkbox" bind:checked={product.selected}>
    </div>
    <div class="item-image">
        <div class="item-image-holder">
            <img src="{product.img}" alt="image"/>
        </div>
    </div>
    <div class="item-info">
        <div class="item-info-text">
            <div class="item-info-text-title">{product.title}</div>
            <div class="item-info-text-description">{product.description}</div>
        </div>
        <div class="item-delete-btn">
            Удалить
        </div>
    </div>
    <div class="item-price">
        <span>{product.price}₽</span>
    </div>
    <div class="item-count">
        <div class="item-count-container">
            <button class="item-count-minus" on:click={() => {
                if (product.count > 0) product.count--
            }}>
                <Icon icon="akar-icons:minus"/>
            </button>
            <input type="text" class="item-count-value" bind:value={product.count}/>
            <button class="item-count-plus" on:click={() => {
            if (product.count < 1000)
                product.count++
            }}>
                <Icon icon="akar-icons:plus"/>
            </button>
        </div>
    </div>
</div>

<style>

    .item {
        padding: 1rem;
        box-sizing: border-box;
        margin: 4px 0;

        display: flex;
        flex-direction: row;
        justify-content: space-between;
        background: #fff;

        border-bottom: 1px solid #F0F0F0;
    }

    .item-checkbox, .item-image, .item-info, .item-price, .item-count {
        display: flex;
        align-items: center;
    }

    .item-image-holder {
        height: 90px;
        width: 90px;
        overflow: hidden;
        display: flex;
        align-items: center;
        justify-content: center;
        border-radius: 6px;
    }

    .item-image img {
        height: 100%;
    }

    .item-info {
        width: 50%;

        display: flex;
        flex-direction: column;
        justify-content: space-between;
        align-items: flex-start;

        font-family: 'Roboto', sans-serif;
        font-size: 14px;
    }

    .item-info-text-title {
        color: #001a34;
        font-weight: 500;
        margin-bottom: 2px;
    }

    .item-info-text-description {
        color: #5a6c7d;
        line-height: 1.29em;
    }

    .item-delete-btn {
        color: #b40021;
        cursor: pointer;
    }

    .item-price {
        margin-top: 4px;

        color: #001a34;
        font-weight: bold;
        flex-direction: column;
        justify-content: flex-start;
    }

    .item-count {
        flex-direction: column;
        justify-content: flex-start;
    }

    .item-count-container {
        display: flex;
        flex-direction: row;
        background-color: #fff;
        border: 2px solid #b3bcc5;
        border-radius: 6px;
        box-sizing: border-box;
        color: #001a34;
    }

    .item-count-value {
        margin: 0;
        padding: 0;
        display: flex;
        align-items: center;
        justify-content: center;
        width: 42px;
        border: none;
        text-align: center;
    }

    .item-count-value:focus {
        outline: none;
    }

    .item-count-container button {
        border: none;
        margin: 0;
        background: #fff;
        cursor: pointer;
    }

    .item-count-container button:hover {
        background: #F0F0F0;
    }

</style>