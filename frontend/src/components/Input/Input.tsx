import {
  useState,
  type CSSProperties,
  type ComponentPropsWithoutRef,
  useEffect,
  useRef
} from "react";
import { useSelector } from "react-redux";
import { type RootState } from "../../features/store";

type InputProps = {
  maxLength?: number;
  classes?: string;
  styles?: CSSProperties;
  format?: string;
  suggestion?: string;
} & ComponentPropsWithoutRef<"input">;

export const countDecimalPlaces = (number) => {
  const numberString = number.toString();
  const decimalIndex = numberString.indexOf(".");

  // If there's no decimal point, return 0
  if (decimalIndex === -1) {
    return 0;
  }

  // Return the number of digits after the decimal point
  return numberString.length - decimalIndex - 1;
};

export default function Input(props: InputProps) {
  const [value, setValue] = useState("");
  const onlyNumbers = /^[0-9]*$/;
  const numberWithSign = /^[+-]\d+$/;
  const [suggestionsVisible, setSuggestionsVisible] = useState(false);
  const suggestionBoxRef = useRef(null);
  // console.log('props.suggestion', props.suggestion)

  const convertStringToArray = (str) => {
    if(!str){
      return []
    }
    if(str){
      const entries = str.slice(1, -1).split(", ");
      return entries.map(entry => {

        const [key, value] = entry.split(":");
        return { [key.trim().replace(/'/g, '').replace(/{/g, '')]: value.trim().replace(/'/g, '').replace(/}/g, '') };
      });
    }
  };

  const suggestions = convertStringToArray(props.suggestion);

  let length = props.maxLength || 10;
  let maxLength = props.maxLength || 10;
  // if acos item, length add 1
  if (props.className?.includes("acos")) {
    length += 1;
  }

  let noBorderPx = 0;
  if (props.className?.includes("noBorder")) {
    noBorderPx = 4;
  }

  let minusPx = 0;
  if (props.className?.includes("acos")) {
    minusPx = 1;
    if (props.className?.includes("textVerticalRight")) {
      minusPx = -1;
    }
  }
  console.log('props.classNameprops.classNameprops.className', props.className)
  if (props.className?.includes("ifdInput")) {
    minusPx = 2;
  }

  // check is format add ',' for number
  if (props.format && props.format.includes(",")) {
    const count = props.format.split(",").length - 1;
    maxLength = maxLength - count;
    if (props.format.includes(".")) {
      const decimalPlaces = countDecimalPlaces(props.format);
      maxLength = maxLength - (decimalPlaces + 1);
    }
  }

  const formatNumber = (num) => {
    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  };

  const handleChange = (e) => {
    console.log('change', e)
    const inputValue = e.target.value;
    if (props.className?.includes("signNumber")) {
      // add +/- begin number
      if (
        numberWithSign.test(inputValue) ||
        inputValue === "" ||
        inputValue === "+" ||
        inputValue === "-"
      ) {
        setValue(inputValue);
      }
    } else if (props.type === "number") {
      // check is number
      let numberValue = inputValue.replace(',','').replace(',','');
      if (onlyNumbers.test(numberValue)) {
        setValue(numberValue);
      }
    } else {
      // default alphabet
      setValue(inputValue);
    }
  };

  function isNumber(value) {
    return !isNaN(parseFloat(value)) && isFinite(value);
  }

  const handleFormat = (e) => {
    const value = e.target.value;
    if (props.className?.includes("textOverflow") && isNumber(value)) {
      const number = +value;
      setValue(number.toString());
    }
  };

  const handleBlur = (e) => {
    const numberOnly = e.target.value.replaceAll(',','');
    let valueFloat = Math.floor(numberOnly)
    const valueDecimal = (Number(numberOnly) - Number(valueFloat)).toFixed(2)

    if (props.format && props.format.includes(",")) {
      let decimalPlaces = 0;
      if (props.format.includes(".")) {
        decimalPlaces = countDecimalPlaces(props.format);
      } 
      let temp = (Number(valueFloat) + Number(valueDecimal))
      .toLocaleString(undefined, { minimumFractionDigits: decimalPlaces, maximumFractionDigits: decimalPlaces });
      setValue(temp);
    }
  };

  const handleInputClick = () => {
    if (props.className?.includes("acos") && suggestions.length > 0) {
      setSuggestionsVisible(true);
    }
  };

  const handleSuggestionClick = (item) => {
    setValue(item);
    setSuggestionsVisible(false);
  };

  const handleOutsideClick = (e) => {
    if (
      suggestionBoxRef.current && 
      !suggestionBoxRef.current.contains(e.target) 
    ) {
      setSuggestionsVisible(false);
    }
  };

  useEffect(() => {
    document.addEventListener('mousedown', handleOutsideClick);
    return () => {
      document.removeEventListener('mousedown', handleOutsideClick);
    };
  }, []);

  const theme = useSelector((state: RootState) => state.theme);

  const inputElement = (
    <input
      {...props}
      maxLength={maxLength}
      type={props.type === "password" ? "password" : "text"}
      className={`input-${theme.name} ${props.className}`}
      style={{ width: `calc(${length}ch - ${noBorderPx}px + ${minusPx}px)`, ...props.styles }}
      onClick={handleInputClick}
      onChange={handleChange}
      onBlur={handleBlur}
      autoComplete="off"
      value={value}
    />
  );

  const popupElement = suggestionsVisible ? (
    <div style={{ position: 'relative', width: '300px' }}>
      {inputElement}
      {suggestionsVisible && suggestions.length > 0 && (
        <div ref={suggestionBoxRef} style={suggestionBoxStyle}>
          {suggestions.map((item, index) => {
            const key = Object.keys(item)[0];
            const value = item[key];
            return (
              <div
                key={index}
                onClick={() => handleSuggestionClick(key)}
                style={suggestionItemStyle}
              >
                {key}:{value}
              </div>
            )
          }
          )}
        </div>
      )}
    </div>
  ) : inputElement;

  return (
    suggestionsVisible ? (
      <div className="outer-div">
        {popupElement}
      </div>
    ) : (
      popupElement
    )
  );
}

const suggestionBoxStyle = {
  position: 'absolute',
  top: '100%',
  left: '0',
  right: '0',
  backgroundColor: '#fff',
  border: '1px solid #ddd',
  maxHeight: '150px',
  overflowY: 'auto',
  zIndex: 1,
};

const suggestionItemStyle = {
  padding: '10px',
  cursor: 'pointer',
  borderBottom: '1px solid #ddd',
  color:'#000'
};