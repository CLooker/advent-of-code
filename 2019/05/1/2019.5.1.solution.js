const solution = (program, input = 1) => {
  for (let i = 0; i < program.length; ) {
    const instructions = program.slice(i, i + 4);
    const opCode = instructions[0] % 100;
    const modes = instructions[0].toString().padStart(5, '0');

    const getParameter = index => {
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
      case 99:
        return;
      default:
        throw new Error('Unrecognized opCode: ' + opCode + ' at index: ' + i);
    }
  }
};

module.exports = solution;
