<script lang="ts">
    import Icon from "@iconify/svelte";
    import {navigate} from "svelte-routing";
    import { createEventDispatcher } from 'svelte';

    const dispatch = createEventDispatcher();
</script>

<header class="header">
    <div class="header-inner">
        <div class="header-inner-logo" on:click={() => navigate('/')}>
            <!-- <img src="/img/Logo.jpg" alt="marketplace logo" /> -->
        </div>
        <div class="header-inner-search">
            <div class="search-field">
                <input class="search-field-input" placeholder="Найти фермерский товар..." />
                <div class="search-field-icon-holder">
                    <Icon class="search-field-icon" icon="bi:search" />
                </div>
            </div>
        </div>
        <div class="header-inner-nav_bar">
            <nav class="nav-bar">
                <div class="nav-bar-item" on:click={() => navigate('/')}>Товары</div>
                <div class="nav-bar-item" on:click={() => dispatch('cartBtn')}>Корзина</div>
                <div class="nav-bar-item" on:click={() => alert("НЕ РАБОТАЕТ")}>Профиль</div>
                <div class="nav-bar-item" on:click={() => alert("НЕ РАБОТАЕТ")}>Заказы</div>
                <div class="nav-bar-item-last"></div>
            </nav>
            <div class="header-inner-enter" on:click={() => dispatch('enterBtn')}>
                <div>Войти</div>
            </div>
        </div>
    </div>
</header>

<style>

    .header {
        height: 4rem;
        width: 100%;

        border-bottom: 1px solid rgba(0,26,52,.16);;
        z-index: 1000;
    }

    .header-inner {
        width: 100%;
        height: 100%;

        padding: 0 16px;
        box-sizing: border-box;

        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;
    }

    .header-inner-logo {
        margin-right: 8px;
        width: 200px;
        cursor: pointer;
    }

    .header-inner-logo > img {
        width: inherit;
    }

    .header-inner-search {
        width: 500px;
    }

    .search-field-input {
        height: 100%;
        width: 100%;
        border: none;

        padding: 0;
        margin: 0;
    }

    .search-field-input:focus {
        outline: none;
    }

    .search-field {
        background: white;
        height: 22px;
        max-width: 100%;

        padding: 12px 14px;
        font-size: 17px;
        border: 1px solid #dddfe2;
        border-radius: 6px;

        display: flex;
        align-items: center;
        justify-content: space-between;
        user-select: none;
    }

    .search-field-icon-holder{
        width: 30px;
        height: 30px;
        cursor: pointer;

        display: flex;
        align-items: center;
        justify-content: center;
    }

    .header-inner-nav_bar, .nav-bar {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        align-items: center;

        height: 100%;
        font-family: 'Roboto', sans-serif;
        font-weight: 400;
        font-size: 18px;
        color: #001a34;
    }

    .nav-bar-item, .header-inner-enter {
        height: 100%;
        padding: 8px 16px;
        box-sizing: border-box;

        display: flex;
        align-items: center;
        cursor: pointer;
    }

    .nav-bar-item-last {
        height: 40px;
        border-right: 1px solid #999999;
    }

    .nav-bar-item:hover, .header-inner-enter:hover {
        background: #f0f0f0;
    }

</style>