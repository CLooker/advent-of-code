const { pipe } = require('../../../utils');
const getRepairedProgram = require('../getRepairedProgram');

const getNounVerbTuple = ints => {
  const targetOutput = 19690720;

  for (let firstInstruction = 0; firstInstruction <= 99; firstInstruction++) {
    for (
      let secondInstruction = 0;
      secondInstruction <= 99;
      secondInstruction++
    ) {
      const repairedProgram = getRepairedProgram(
        [...ints],
        [firstInstruction, secondInstruction]
      );
      const output = repairedProgram[0];
      if (output === targetOutput) return [firstInstruction, secondInstruction];
    }
  }
};

const solution = ints =>
  pipe(getNounVerbTuple, tuple =>
    tuple
      .map(item => {
        const itemStr = item.toString();
        return itemStr.length === 2 ? itemStr : '0' + itemStr;
      })
      .join('')
  )(ints);

module.exports = solution;
