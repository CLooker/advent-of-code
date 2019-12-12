const { first, pipe } = require("../../../utils");

const getRepairedProgram = ints => {
  ints[1] = 12;
  ints[2] = 2;

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
        throw new Error("Unrecognized opCode: " + opCode + " at index: " + i);
    }
  }

  return ints;
};

module.exports = ints => pipe(getRepairedProgram, first)(ints);
