#3D plot

import pandas as pd 
import seaborn as sns
import matplotlib.pyplot as plt
import numpy as np
import scipy.stats as stats
# This import registers the 3D projection, but is otherwise unused.
from mpl_toolkits.mplot3d import Axes3D  # noqa: F401 unused import
import sys


paretoFront = str(sys.argv[1])
#paretoFront = "/Users/sgerasimou/Documents/Git/EvoChecker/data/DPM/NSGAII/DPM_NSGAII_104753_281020_Front"

f = pd.read_csv(paretoFront, header=0, delimiter="\t")

fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')
ax.scatter(f.iloc[:,0], f.iloc[:,1], f.iloc[:,2])

ax.set_xlabel(f.columns[0])
ax.set_ylabel(f.columns[1])
ax.set_zlabel(f.columns[2])

plt.show()

print("Done")