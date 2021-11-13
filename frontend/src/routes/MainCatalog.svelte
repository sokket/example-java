<script>
    import { testTovars } from '../testTovars.js';
    import Card from "../components/Card.svelte";
</script>

<main id="mainContainer">
    <div class="mainContainer-inner">
        <div class="wrapper">
            <div class="wrapper-banner">
                <div class="banner-container">
                    <!--<img class="banner-container-image" src="/img/Background.jpg" alt="banner-background"/>-->
                </div>
            </div>
            <div class="wrapper-products-card">
                <h1 class="wrapper-products-card-title"> Товары </h1>
                <div class="wrapper-products-card-container">
                    {#each testTovars as product}
                        <Card {product}/>
                    {/each}
                </div>
            </div>
            <div class="wrapper-footer">
                <!--TODO:-->
            </div>
        </div>
    </div>
</main>

<style>

    #mainContainer {
        width: 100vw;
        min-height: calc(100vh - 4rem - 1px);
        height: calc(100vh - 4rem - 1px);
        overflow-y: auto;
        overflow-x: hidden;
        z-index: 1;
    }

    .mainContainer-inner {
        width: 100%;
        height: 100%;

        padding: 2rem;
        box-sizing: border-box;
    }

    .wrapper {
        height: 100%;
        width: 100%;
    }

    .wrapper-banner {
        width: 100%;
        height: 370px;

        display: flex;
        justify-content: center;
    }

    .banner-container {
        width: 100%;
        min-width: 650px;
        max-height: 370px;

        display: flex;
        justify-content: center;

        background: url("/img/Background.jpg") center bottom no-repeat;
        background-size: cover;

        border-radius: 16px;
    }

    .banner-container-image {
        display: block;
        width: 100%;
        object-fit: cover;
        border-radius: 16px;
    }

    .wrapper-products-card {
        margin-top: 10px;

        display: flex;
        flex-direction: column;
    }

    .wrapper-products-card-title {
        margin: 16px 0;
        font-size: 36px;
    }

    .wrapper-products-card-container {
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;
        align-items: baseline;
    }

</style>