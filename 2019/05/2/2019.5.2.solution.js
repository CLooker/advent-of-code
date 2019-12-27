const solution = (program, input = 5) => {
  for (let i = 0; i < program.length; ) {
    const instructions = program.slice(i, i + 4);
    const opCode = instructions[0] % 100;
    const modes = instructions[0].toString().padStart(5, '0');

    const getParameter = index => {
      if (index === 3) return instructions[3];
      const isPositionMode = modes[3 - index] === '0';
      const valueIndex = isPositionMode ? instructions[index] : index;
      const parameter = isPositionMode
        ? program[valueIndex]
        : instructions[valueIndex];
      return parameter;
    };

    switch (opCode) {
      case 1: {
        i += 4;
        const storageIndex = instructions[3];
        const value = getParameter(1) + getParameter(2);
        program[storageIndex] = value;
        break;
      }
      case 2: {
        i += 4;
        const storageIndex = instructions[3];
        const value = getParameter(1) * getParameter(2);
        program[storageIndex] = value;
        break;
      }
      case 3: {
        i += 2;
        const storageIndex = instructions[1];
        const value = input;
        program[storageIndex] = value;
        break;
      }
      case 4: {
        i += 2;
        const value = getParameter(1);
        console.log(value);
        if (value) return value;
        break;
      }
      case 5: {
        const [firstParameter, secondParameter] = [1, 2].map(getParameter);
        i = firstParameter ? secondParameter : i + 3;
        break;
      }
      case 6: {
        const [firstParameter, secondParameter] = [1, 2].map(getParameter);
        i = firstParameter ? i + 3 : secondParameter;
        break;
      }
      case 7: {
        i += 4;
        const [firstParameter, secondParameter, thirdParameter] = [1, 2, 3].map(
          getParameter
        );
        const storageIndex = thirdParameter;
        const value = firstParameter < secondParameter ? 1 : 0;
        program[storageIndex] = value;
        break;
      }
      case 8: {
        i += 4;
        const [firstParameter, secondParameter, thirdParameter] = [1, 2, 3].map(
          getParameter
        );
        const storageIndex = thirdParameter;
        const value = firstParameter === secondParameter ? 1 : 0;
        program[storageIndex] = value;
        break;
      }
      case 99:
        return;
      default:
        throw new Error('Unrecognized opCode: ' + opCode + ' at index: ' + i);
    }
  }
};

module.exports = solution;
