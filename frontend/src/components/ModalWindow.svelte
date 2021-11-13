<script>
    import {createEventDispatcher, onDestroy} from 'svelte';

    const dispatch = createEventDispatcher();
    const close = () => dispatch('close');

    let modal;

    const handle_keydown = e => {
        if (e.key === 'Escape') {
            close();
            return;
        }

        if (e.key === 'Tab') {
            // trap focus
            const nodes = modal.querySelectorAll('*');
            const tabbable = Array.from(nodes).filter(n => n.tabIndex >= 0);

            let index = tabbable.indexOf(document.activeElement);
            if (index === -1 && e.shiftKey) index = 0;

            index += tabbable.length + (e.shiftKey ? -1 : 1);
            index %= tabbable.length;

            tabbable[index].focus();
            e.preventDefault();
        }
    };

    const previously_focused = typeof document !== 'undefined' && document.activeElement;

    if (previously_focused) {
        onDestroy(() => {
            previously_focused.focus();
        });
    }
</script>

<svelte:window on:keydown={handle_keydown}/>

<div class="modal-background" on:click={close}></div>

<div class="modal" role="dialog" aria-modal="true" bind:this={modal}>
    <div class="modal-container">
        <div class="header">
            <slot name="header"></slot>
        </div>
        <slot></slot>
    </div>
</div>

<style>
    .modal-background {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: none;

        z-index: 1000;
    }

    .modal {
        position: absolute;
        left: 50%;
        top: 50%;
        width: calc(100vw - 4em);
        max-height: 580px;
        transform: translate(-50%, -50%);
        padding: 1em;
        background: none;

        background: white;
        border-radius: 10px;

        max-width: 580px;
        box-shadow: 0 15px 50px 0 rgb(0 0 0 / 35%);
        flex-grow: 0;

        overflow-y: hidden;

        z-index: 1000;
    }

    .modal-container {

    }

    .header::after {
        content: "";
        display: block;

        width: 100%;
        height: 1px;

        background-color: #F0F0F0;;
        position: absolute;
        left: 0;
        z-index: 1000;
    }

</style>