const getRepairedProgram = (ints, startingInstructions = [12, 2]) => {
  ints[1] = startingInstructions[0];
  ints[2] = startingInstructions[1];

  for (let i = 0; i < ints.length; i += 4) {
    const [opCode, indexOfNum1, indexOfNum2, indexOfResult] = ints.slice(
      i,
      i + 4
    );

    const [num1, num2] = [indexOfNum1, indexOfNum2].map(index => ints[index]);

    switch (opCode) {
      case 1:
        ints[indexOfResult] = num1 + num2;
        break;
      case 2:
        ints[indexOfResult] = num1 * num2;
        break;
      case 99:
        return ints;
      default:
        throw new Error('Unrecognized opCode: ' + opCode + ' at index: ' + i);
    }
  }

  return ints;
};

module.exports = getRepairedProgram;
