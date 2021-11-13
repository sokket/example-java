<script>
    export const url = '';

    let tovarAdd = false;

    let data = {
        title: '',
        type: '',
        price: '',
        description: ''
    };

    let tovarList = [];

    fetch('').then(result => tovarList = result);
</script>

<div class="lk_container">
    <div class="aside">
        
    </div>
    <div class="main">
        <div class="addTovarContainer">
            {#if !tovarAdd}
                <div class="addTovarButton-open">Добавить товар</div>
            {:else}
                <div class="addTovarButton-close">Отмена</div>
                <div class="addTovarWrapper">
                    <div class="inputContainer"><InputField bind:bindText={data.title} placeholderText="Название" /></div>
                    <div class="inputContainer"><InputField bind:bindText={data.type} placeholderText="Категория" /></div>
                    <div class="inputContainer"><InputField bind:bindText={data.price} placeholderText="Цена" /></div>
                    <div class="inputContainer"><InputField bind:bindText={data.description} placeholderText="Описание" /></div>  
                </div>
            {/if}
        </div>
        <div class="listTovars">
            <ul>
                {#each tovarList as tovar}
                    <li id={tovar.id}>
                        <div class="tovar-name">{tovar.name}</div>
                        <div class="tovar-category">{tovar.type}</div>
                        <div class="tovar-price">{tovar.price}</div>
                        <img src={tovar.img} alt="tovar image" />
                    </li>
                {/each}
            </ul>
        </div>
    </div>
</div>

<style>

    .lk_container {
        display: flex;
    }

    .aside {
        background-color: #eee;
        width: 30%;
    }

    .main {
        padding: 15px;
    }

    .addTovarContainer {

    }

    .addTovarButton-open {

    }

    .addTovarButton-close {

    }

    .addTovarWrapper {

    }

    .inputContainer {

    }

</style>