/*
    NON E' IN USO MA PUO' TORNARE UTILE NEL CASO SI VADA AD USARE react-select
*/

/*
    ...provided mantiene gli stili di default
    state ti permette di accedere ai props passati su <Select />

    option = e' la lista di tutti gli oggetti
    container
    control = e' la textbox di ricerca, e' possibile inserirci un bottone
    dropdownIndicator
    indicatorSeparator
    valueContainer
*/
// ==============================================================================
/*
    option: (provided, state) => ({
        borderBottom: '1px dotted pink',
        color: state.isSelected ? 'red' : 'blue',
    })

    <controller>
        <valueContainer></valueContainer>
        <dropdownIndicator></dropdownIndicator>
    </controller>
    <menu>
        <menuList>
            <option></option>
        </menuList>
    </menu>

    state
        isDisabled
        isFocused
        isSelected

*/
export const SelectDropdownBoxStyle = {
    option: (styles, state) => {
        return {
            ...styles,
            backgroundColor: state.isSelected ? "#eee" : "",
            color: '#FFF',
            cursor: state.isDisabled ? 'not-allowed' : 'default',
        }
    },
    control: base => ({
        ...base,
        backgroundColor: 'pink'
    }),
    valueContainer: () => ({
        backgroundColor: 'orange'
    }),
    dropdownIndicator: () => ({
        backgroundColor: 'yellow'
    }),
    indicatorSeparator: (base) => ({
        ...base,
        width: "10px",
        backgroundColor: 'black'
    }),
    menu: () => ({
        backgroundColor: 'orange'
    }),
    menuList: () => ({
        backgroundColor: 'black'
    }),
}