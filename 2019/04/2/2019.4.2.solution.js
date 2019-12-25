const {
  getValidPasswordsTotal,
  hasLengthOfSix,
  hasNoDescendingItem,
  hasRepeatingItem
} = require('../2019.4.common');

const hasValidRepeatingItem = items => {
  const itemToFreq = items.reduce((accum, item, i) => {
    const nextItem = items[i + 1];

    if (item === nextItem) {
      if (item in accum) accum[item]++;
      else accum[item] = 2;
    }

    return accum;
  }, {});

  return Object.values(itemToFreq).some(freq => freq === 2);
};

const validityFns = [
  hasLengthOfSix,
  hasNoDescendingItem,
  hasRepeatingItem,
  hasValidRepeatingItem
];

const solution = getValidPasswordsTotal(validityFns);

module.exports = solution;
