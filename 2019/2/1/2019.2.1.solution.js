const { first, pipe } = require('../../../utils');
const getRepairedProgram = require('../getRepairedProgram');

const solution = ints => pipe(getRepairedProgram, first)(ints);

module.exports = solution;
